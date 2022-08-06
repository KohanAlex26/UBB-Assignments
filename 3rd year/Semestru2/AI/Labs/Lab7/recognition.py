import speech_recognition as sr

# with sr.AudioFile('The phone is ringing.wav') as source:
#     r = sr.Recognizer()
#     r.energy_threshold = 1200
#     print(r.recognize_google(r.record(source)))

with sr.Microphone() as source:
    r = sr.Recognizer()
    r.energy_threshold = 1200
    print(r.recognize_google(r.record(source, duration=10)))