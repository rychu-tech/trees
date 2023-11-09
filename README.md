# Trees Application

## Setting up

To run the application, navigate to the main folder and run commands:
1. To generate jar file
```
mvn package -Dmaven.test.skip
```
or
```
mvn package "-Dmaven.test.skip"
```
2. To set up docker containers
```
docker-compose up --build
```
## General info

- Backend is set up on port 8080
- Frontend is set up on port 3000
- Database is set up on port 5432

## Endpoints

| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| POST | /trees | name: string | Adds new tree |
| GET | /trees | | Returns list of trees |
| GET | /trees/{id} | | Returns tree |
| DEL | /trees/{id} | | Deletes tree |
| POST | /trees/{id}/nodes | value: Integer, parentNodeId: Integer | Adds new node to tree |
| PUT | /trees/{id}/nodes/{id} | value: Integer, parentNodeId: Integer | Edits tree node |
| DEL | /trees/{id}/nodes/{id} |  | Removes node from tree |
