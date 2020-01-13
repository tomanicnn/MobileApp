import flask, json
from flask import Flask, request

app = Flask('__main__', template_folder="", static_folder="", root_path="", static_url_path="")
msgs = []

@app.route('/')
def index_page():
    return ("Hello")

@app.route('/register', methods=['POST'])
def register():
    user = flask.request.form
    userDict = user.to_dict(flat=True)
    userDict['isAdmin'] = False
    data = []
    try:
        with open('users.json', 'r', encoding='utf-8') as f:
            data = json.load(f)
            data.append(userDict)
        with open('users.json', 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False)
            return "OK"
    except:
        return "FAIL"

@app.route('/addRest', methods=['POST'])
def addRest():
    user = flask.request.form
    userDict = user.to_dict(flat=True)
    data = []
    try:
        with open('restourans.json', 'r', encoding='utf-8') as f:
            data = json.load(f)
            data.append(userDict)
        with open('restourans.json', 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False)
            return "OK"
    except:
        return "FAIL"

@app.route('/removeRest', methods=['POST'])
def removeRest():
    id = flask.request.form
    id = id.to_dict(flat=True)
    id = int(id['id'])
    data = []
    try:
        with open('restourans.json', 'r', encoding='utf-8') as f:
            data = json.load(f)
            data.pop(id)
        with open('restourans.json', 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False)
            return "200"
        
    except:
        return "FAIL"


# TODO Getting null from nameOfRest
@app.route('/addFood', methods=['POST'])
def addFood():
    infos = flask.request.form
    infos = infos.to_dict(flat=True)
    nameOfRest = infos['nameOfRest']
    print(nameOfRest)
    print(infos) 
    return "OK" 

# TODO : Create modify rest

# TODO : Create add food TODO !
# TODO : Create delete food
# TODO : Create modify food

@app.route('/json/<number>')
def show_food(number = None):
    rests = []
    try:
        with open('restourans.json', encoding='utf-8') as f:
            data = json.load(f)
            for i in data:
                if i['id'] == number:
                    a = 0
                    while a < len(i['listOfFood']):
                        rests.append(i['listOfFood'][a])
                        a+= 1
            return json.dumps(rests)

    except:
        return "FAIL"
     
app.run("0.0.0.0", 5000)
