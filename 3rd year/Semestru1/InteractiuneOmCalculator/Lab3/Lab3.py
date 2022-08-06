import requests
import webbrowser

response = requests.get("https://randomfox.ca/floof")
#print(response.status_code)
#print(response.text)
fox = response.json()
print(fox['image'])
webbrowser.open(fox['image'], new=1)