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
Tą samą komendę można uruchomić w terminalu po otworzeniu projektu w IDE (np. Intellij).

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
                        {"name":"Bob"},
                        {"name":"Logan"},
                        {"name":"Alice"}
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
Content           : {"name":"Bob"}
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
Content           : [{"id":4,"placeNumber":1,"tier":1,"placeForDisabledPeople":true}]
```
### POST `host:port`/agents?name={name}
Dodaje nowego agenta.

`name` - należy wpisać imię agenta, którego chcemy dodać 

Curl:
```shell
curl --request POST --url "http://localhost:8080/agents?name=Louis"
```
Odpowiedź:
```
StatusCode        : 201
Content           : No response body 
```

### POST `host:port`agents/reservations?name={name}&place={placeId}
Dodaje rezerwację dla konkretnego agenta.

`name` - należy wpisać imię agenta dokonującego rezerwacji

`placeId` - należy wpisać id miejsca parkingowego 


Curl:
```shell
curl --request POST --url "http://localhost:8080/agents/reservations?name=Bob&place=7"
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
curl --request DELETE --url http://localhost:8080/agents/Lou
```
Odpowiedź:
```
StatusCode        : 200
Content           : User successfully deleted
```

### DELETE `host:port`/agents/reservations/{placeId}
Usuwa rezerwację.

`placeId` - w tym polu należy podać id miejsca, dla którego rezerwację chce się usunać

Curl:
```shell
curl --request DELETE --url http://localhost:8080/agents/reservations/7 
```
Odpowiedź:
```
StatusCode        : 200
Content           : Reservation successfully deleted
```
## Parking place - endpointy API
### GET `host:port`/places
Wyświetla listę wszystkich miejsc.

Curl:
```shell
curl http://localhost:8080/places
```
Odpowiedź:
```
StatusCode        : 200
Content           : [
                        {"id":4,"placeNumber":1,"tier":1,"placeForDisabledPeople":true},
                        {"id":6,"placeNumber":3,"tier":1,"placeForDisabledPeople":false},
                        {"id":7,"placeNumber":4,"tier":1,"placeForDisabledPeople":false},
                        {"id":8,"placeNumber":1,"tier":2,"placeForDisabledPeople":true}
                    ]
```

### GET `host:port`/places/{tier}/{place}
Wyświetla informację o konkretnym miejscu. 

`tier` - należy wpisać numer piętra

`place` - należy wpisać numer miejsca 

Curl:
```shell
curl --request GET --url http://localhost:8080/places/1/2
  ```
Odpowiedź:
```
StatusCode        : 200
Content           : {"id":4,"placeNumber":1,"tier":1,"placeForDisabledPeople":true}
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
                        {"id":6,"placeNumber":3,"tier":1,"placeForDisabledPeople":false},
                        {"id":7,"placeNumber":4,"tier":1,"placeForDisabledPeople":false},
                        {"id":8,"placeNumber":1,"tier":2,"placeForDisabledPeople":true}
                    ]

```

### POST `host:port`/places?nr={nr}&tier={tier}&isDisabled={isDisabled}
Dodaje nowe miejsce parkingowe.

`nr` - należy wpisać numer miejsca parkingowego

`tier` - należy wpisać piętro na którym znajduje się miejsce

`isDisabled` - należy wpisać, czy miejsce jest przeznaczone dla osób niepełnosprawnych (true/false)

Curl:
```shell
curl --request POST --url "http://localhost:8080/places?nr=3&tier=3&isDisabled=false" 
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
curl --request DELETE --url http://localhost:8080/places/6
```
Odpowiedź:
```
StatusCode        : 200
Content           : Place successfully deleted
```

### Wszystkie komendy `curl` powinny być odpalane z poziomu terminala Windows (cmd)

## Wymagania

- Java 11
- Maven
- Docker
- Ewentualnie Insomnia/Postman do testowania manualnego
