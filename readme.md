# Parking lot

## Opis aplikacji

Aplikacja służy do rezerwacji miejsc w jednobudynkowym parkingowcu. Obsługuje zarówno dodanie, usunięcie jak i
wyświetlenie miejsc parkingowych oraz agentów rezerwujących miejsca. Za jej pomocą w prosty sposób można dokonać
bezterminowej rezerwacji miejsca.

## Zbudowanie aplikacji
Aby zbudować aplikację należy w folderze projektu uruchomić terminal (cmd). W terminalu należy wywołać poniższą komendę:
```shell
./mvnw spring-boot:build-image
```
Tą samą komendę można uruchomić w terminalu  po otworzeniu projektu w IDE (np. Intellij).
## Uruchomienie aplikacji
Należy zainstalować Dockera. Następnie w folderze projektu należy uruchomić terminal (cmd). W terminalu wpisać poniższą komendę, za pomocą której zostanie utworzony oraz uruchomiony kontener Dockera. 
```shell
docker-compose up
```
Tą samą komendę można uruchomić w terminalu  po otworzeniu projektu w IDE (np. Intellij).

## Agent - endpointy API
### GET `host:port`/agents
Wyświetla listę wszystkich agentów

Curl: 
```shell
curl http://localhost:8080/agents
```
Odpowiedź: 
```
StatusCode        : 200
Content           : [
                        {"id":1,"name":"Bob"},
                        {"id":2,"name":"Logan"},
                        {"id":3,"name":"Alice"}
                    ]
```

### GET `host:port`/agents/{name}
Wyświetla informację o konkretnym agencie. 

`name` - Imię konkretnego agenta, dla którego chcemy wyszukać informacje. 

Curl:
```shell
curl http://localhost:8080/agents/Bob
```
Odpowiedź:
```
StatusCode        : 200
Content           : {"id":1,"name":"Bob"}
```

### GET `host:port`/agents/reservations/{name}
Wyświetla informację o wszystkich miejscach zarezerwowanych przez agenta. 

`name` - Imię konkretnego agenta, dla którego chcemy wyświetlić rezerwacje.

Curl:
```shell
curl http://localhost:8080/agents/reservations/Bob
```
Odpowiedź:
```
StatusCode        : 200
Content           : [{"id":4,"placeNumber":1,"tier":1,"placeForDisabledPeople":true,"reserved":true}]
```
### POST `host:port`/agents
Dodaje nowego agenta. 

JSON body: 
```shell
{
	"name": "Rafael"
}
```
`name` - należy wpisać imię agenta, którego chcemy dodać 

Curl:
```shell
curl --request POST --url http://localhost:8080/agents --header 'Content-Type: application/json' --data '{"name": "Rafael"}'
```
Odpowiedź:
```
StatusCode        : 201
Content           : No response body 
```

### POST `host:port`/agents/reservations
Dodaje rezerwację dla konkretnego agenta.

JSON body: 
```shell
{
	"parkingPlaceDTO": {
		"id" : 5
	},
	"agentDTO":{
		"name": "John"
	}
}
```
`id` - należy wpisać id miejsca parkingowego 

`name` - należy wpisać imię agenta dokonującego rezerwacji

Curl:
```shell
curl --request POST --url http://localhost:8080/agents/reservations --header 'Content-Type: application/json' --data '{"parkingPlaceDTO": {"id" : 5},"agentDTO":{"name": "John"}}'
```
Odpowiedź:
```
StatusCode        : 201
Content           : No response body
```
### DELETE `host:port`/agents/{name}
Usuwa konkretnego agenta. 

`name` - w tym polu należy podać nazwę agenta, którego chce się usunać

Curl:
```shell
curl --request DELETE --url http://localhost:8080/agents/Rafael
```
Odpowiedź:
```
StatusCode        : 200
Content           : User successfully deleted
```

### DELETE `host:port`/agents/reservations/{placeId}
Usuwa rezerwację dla konkretnego agenta.

`placeId` - w tym polu należy podać id miejsca, dla którego rezerwację chce się usunać

Curl:
```shell
curl --request DELETE --url http://localhost:8080/agents/reservations/5 
```
Odpowiedź:
```
StatusCode        : 200
Content           : Reservation successfully deleted, 
```
## Parking place - endpointy API
### GET `host:port`/places
Wyświetla listę wszystkich agentów

Curl:
```shell
curl http://localhost:8080/places
```
Odpowiedź:
```
StatusCode        : 200
Content           : [
                        {"id":4,"placeNumber":1,"tier":1,"placeForDisabledPeople":true,"reserved":true},
                        {"id":6,"placeNumber":3,"tier":1,"placeForDisabledPeople":false,"reserved":false},
                        {"id":7,"placeNumber":4,"tier":1,"placeForDisabledPeople":false,"reserved":false},
                        {"id":8,"placeNumber":1,"tier":2,"placeForDisabledPeople":true,"reserved":false}
                    ]
```

### GET `host:port`/places/{tier}/{place}
Wyświetla informację o konkretnym miejscu. 

`tier` - należy wpisać numer piętra

`place` - należy wpisać numer miejsca 

Curl:
```shell
curl --request GET --url http://localhost:8080/places/1/1
  ```
Odpowiedź:
```
StatusCode        : 200
Content           : {"id":4,"placeNumber":1,"tier":1,"placeForDisabledPeople":true,"reserved":true}
```

### GET `host:port`/free-places
Wyświetla listę wszystkich wolnych miejsc. 
Curl:
```shell
curl --request GET --url http://localhost:8080/free-places 
```
Odpowiedź:
```
StatusCode        : 200
Content           : [
                        {"id":6,"placeNumber":3,"tier":1,"placeForDisabledPeople":false,"reserved":false},
                        {"id":7,"placeNumber":4,"tier":1,"placeForDisabledPeople":false,"reserved":false},
                        {"id":8,"placeNumber":1,"tier":2,"placeForDisabledPeople":true,"reserved":false},
                        {"id":19,"placeNumber":2,"tier":2,"placeForDisabledPeople":true,"reserved":false},
                        {"id":20,"placeNumber":3,"tier":2,"placeForDisabledPeople":false,"reserved":false}
                    ]

```

### POST `host:port`/places
Dodaje nowe miejsce parkingowe.

JSON body:
```shell
{
	"placeNumber":"1",
	"tier":"2",
	"placeForDisabledPeople": "true"
}
```
`placeNumber` - należy wpisać numer miejsca parkingowego

`tier` - należy wpisać piętro na którym znajduje się miejsce

`placeForDisabledPeople` - należy wpisać, czy miejsce jest przeznaczone dla osób niepełnosprawnych (true/false)

Curl:
```shell
curl --request POST --url http://localhost:8080/places --header 'Content-Type: application/json' --data '{	"placeNumber":"2",	"tier":"2",	"placeForDisabledPeople": "true"}'
 ```
Odpowiedź:
```
StatusCode        : 201
Content           : No response body
```
### DELETE `host:port`/places/{id}
Usuwa miejsce parkingowe. 

`id` - w tym polu należy podać id miejsca, które chcemy usunąć

Curl:
```shell
curl --request DELETE --url http://localhost:8080/places/20
```
Odpowiedź:
```
StatusCode        : 200
Content           : Place successfully deleted
```

###Wszystkie komendy `curl` powinny być odpalane z poziomu terminala Windows (cmd)

###Wymagania

- Java 11
- Maven
- Docker
- Ewentualnie Insomnia/Postman do testowania manualnego
