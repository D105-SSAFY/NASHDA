package com.ssafy.nashda.question.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.dto.request.QuestionReqDto;
import com.ssafy.nashda.question.dto.response.QuestionFileResDto;
import com.ssafy.nashda.question.dto.response.QuestionResDto;
import com.ssafy.nashda.question.dto.response.ReplyResDto;
import com.ssafy.nashda.question.entity.Question;
import com.ssafy.nashda.question.entity.QuestionFile;
import com.ssafy.nashda.question.entity.Reply;
import com.ssafy.nashda.question.repository.QuestionFileRepository;
import com.ssafy.nashda.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionFileRepository questionFileRepository;
    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public void createQuestion(Member member, QuestionReqDto questionReqDto, List<MultipartFile> files) {

        if (questionReqDto.getTitle() == null || questionReqDto.getTitle().trim().isEmpty()) {
            throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
        }

        if (questionReqDto.getContent() == null || questionReqDto.getContent().trim().isEmpty()) {
            throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
        }

        Question question = questionRepository.save(questionReqDto.toEntity(member));

        if (files != null) {
            for(MultipartFile file : files) {
                String uploadUrl;
                try {
                    if (file.getOriginalFilename().endsWith(".txt")) {
                        continue;
                    } else {
                        uploadUrl = s3Uploader.uploadFiles(file, "question-files");
                    }
                } catch (IOException e) {
                    throw new BadRequestException(ErrorCode.FAIL_UPLOAD_FILE);
                }

                QuestionFile questionFile = QuestionFile.builder()
                        .question(question)
                        .url(uploadUrl)
                        .fileName(file.getOriginalFilename())
                        .build();

                questionFileRepository.save(questionFile);
            }
        }
    }

    @Override
    @Transactional
    public List<QuestionResDto> getQuestions(Member member) {
        List<Question> questions = questionRepository.findByMember(member);
        List<QuestionResDto> result = new ArrayList<>();

        if (questions != null) {
            for (Question question : questions) {
                Reply reply = question.getReply();
                ReplyResDto replyResDto = (reply != null) ? new ReplyResDto(reply) : null;

                List<QuestionFileResDto> files = new ArrayList<>();
                files = question.getFiles().stream()
                        .map(QuestionFileResDto::new)
                        .collect(Collectors.toList());

                QuestionResDto questionResDto = new QuestionResDto(question, replyResDto, files);

                result.add(questionResDto);
            }
        } else {
            throw new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION);
        }

        return result;
    }

    @Override
    @Transactional
    public QuestionResDto getQuestion(Member member, Long index) {

        Question question = questionRepository.findById(index).orElseThrow(() -> {
            throw new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);
        });

        if (!member.equals(question.getMember())) {
            throw new BadRequestException(ErrorCode.NOT_EQUAL_USER);
        }

        Reply reply = question.getReply();
        ReplyResDto replyResDto = (reply != null) ? new ReplyResDto(reply) : null;

        List<QuestionFileResDto> files = new ArrayList<>();
        if (question.getFiles() != null) {
            files = question.getFiles().stream()
                    .map(QuestionFileResDto::new)
                    .collect(Collectors.toList());
        }

        QuestionResDto questionResDto = new QuestionResDto(question, replyResDto, files);
        questionResDto.setReply(replyResDto);

        return questionResDto;
    }


    @Transactional
    public void updateQuestion(Member member, Long index, QuestionReqDto questionReqDto, List<MultipartFile> files) {
        Question question = questionRepository.findById(index).orElseThrow(() -> {return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);});

        if (member.getMemberNum().equals(question.getMember().getMemberNum())) {
            if (questionReqDto != null) {
                String title = questionReqDto.getTitle();
                String content = questionReqDto.getContent();

                if (title == null || title.trim().isEmpty()) {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_TITLE);
                } else {
                    question.setTitle(title);
                }

                if (content == null || content.trim().isEmpty()) {
                    throw new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT);
                } else {
                    question.setContent(content);
                }

                List<QuestionFile> oldFiles = new ArrayList<>(question.getFiles());
                List<String> newFileNames = new ArrayList<>();

                if (files != null) {

                    for(MultipartFile file : files) {
                        String fileName = file.getOriginalFilename();
                        newFileNames.add(file.getOriginalFilename());

                        if (oldFiles.stream().anyMatch(of -> of.getFileName().equals(fileName))) continue;

                        String uploadUrl;
                        try {
                            if (file.getOriginalFilename().endsWith(".txt")) {
                                continue;
                            } else {
                                uploadUrl = s3Uploader.uploadFiles(file, "question-files");
                            }
                        } catch (IOException e) {
                            throw new BadRequestException(ErrorCode.FAIL_UPLOAD_FILE);
                        }

                        QuestionFile questionFile = QuestionFile.builder()
                                .question(question)
                                .url(uploadUrl)
                                .fileName(file.getOriginalFilename())
                                .build();

                        questionFileRepository.save(questionFile);
                    }
                }

                Iterator<QuestionFile> iterator = question.getFiles().iterator();
                while (iterator.hasNext()) {
                    QuestionFile oldFile = iterator.next();
                    if (!newFileNames.contains(oldFile.getFileName())) {
                        iterator.remove();
                        questionFileRepository.delete(oldFile);
                    }
                }
                return;
            }
        }
        throw new BadRequestException(ErrorCode.NOT_EQUAL_USER);
    }

    @Transactional
    public void deleteQuestion(Member member, Long index) {
        Question question = questionRepository.findById(index).orElseThrow(()-> {return new BadRequestException(ErrorCode.NOT_EXISTS_QUESTION_ID);});

        if (member.getMemberNum().equals(question.getMember().getMemberNum())) {
            questionRepository.deleteById(index);
            return;
        }
        throw new BadRequestException(ErrorCode.NOT_EQUAL_USER);
    }
}
