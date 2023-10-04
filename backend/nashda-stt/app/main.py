import argparse
import os
from fastapi import FastAPI, File, UploadFile
from fastapi.middleware.cors import CORSMiddleware
from transformers import pipeline
from g2pk import G2p

app = FastAPI()
g2p = G2p()

origins = [
    "*",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

model_01 = "openai/whisper-base"

transcribe_01 = pipeline(
    task="automatic-speech-recognition",
    model=model_01,
    chunk_length_s=30,
    device=-1,
)

transcribe_01.model.config.forced_decoder_ids = transcribe_01.tokenizer.get_decoder_prompt_ids(language="Korean", task="transcribe")


model_02 = "openai/whisper-small"

transcribe_02 = pipeline(
    task="automatic-speech-recognition",
    model=model_02,
    chunk_length_s=30,
    device=-1,
)

transcribe_02.model.config.forced_decoder_ids = transcribe_02.tokenizer.get_decoder_prompt_ids(language="Korean", task="transcribe")


@app.post("/pron")
async def file(file_upload: UploadFile):
    try:
        data = await file_upload.read()

        with open("audio.wav", "wb") as f:
            f.write(data)
        

        outputs = transcribe_01("audio.wav")["text"][1:]    
        
        outputs = outputs.split(' ')

        rst = ""

        for text in outputs:
            rst += g2p(text) + ' '

        rst = rst[:-1]
        rst = rst.replace('.', '').replace('?', '').replace(',', '').replace('!', '')

        return { "status": "200", "message" : "STT 결과", "data": rst}
    except Exception as e:
        print("Error occured :", e)
        return {"status" : "400", "message" : "STT 변환 실패", "data" : e}

@app.post("/stt")
async def file(file_upload: UploadFile):
    try:
        data = await file_upload.read()

        with open("audio.wav", "wb") as f:
            f.write(data)
        

        output = transcribe_02("audio.wav")["text"][1:]

        return { "status": "200", "message" : "STT 결과", "data": output}
    except Exception as e:
        print("Error occured :", e)
        return {"status" : "400", "message" : "STT 변환 실패", "data" : e}