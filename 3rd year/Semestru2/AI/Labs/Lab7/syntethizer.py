import pyttsx3

synthesizer = pyttsx3.init()
voices = synthesizer.getProperty('voices')
for voice in voices:
    print("Voice:")
    print("ID: %s" %voice.id)
    print("Name: %s" %voice.name)
    print("Age: %s" %voice.age)
    print("Gender: %s" %voice.gender)
    print("Languages Known: %s" %voice.languages)
synthesizer.setProperty('voice', voices[1].id)
synthesizer.say("Please Read This: The End User License Agreement does not allow for commercial use or distribution of audio created using the Ivona™ voices. Please contact support@textaloud.com if you need this.")
synthesizer.runAndWait()
synthesizer.stop()
