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

model_id = "openai/whisper-base"

transcribe = pipeline(
    task="automatic-speech-recognition",
    model=model_id,
    chunk_length_s=30,
    device=-1,
)

transcribe.model.config.forced_decoder_ids = transcribe.tokenizer.get_decoder_prompt_ids(language="Korean", task="transcribe")


@app.post("/pron")
async def file(file_upload: UploadFile):
    try:
        data = await file_upload.read()

        with open("audio.wav", "wb") as f:
            f.write(data)
        

        outputs = transcribe("audio.wav")["text"][1:]    
        
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


