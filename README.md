# Trees Application

## Setting up

To run the application, navigate to the backend folder and run command:
1. To generate jar file
```
mvn package -Dmaven.test.skip
```
or
```
mvn package "-Dmaven.test.skip"
```
2. To set up docker containers go to main folder and run:
```
docker-compose up --build
```
## General info

- Backend is set up on port 8080
- Frontend is set up on port 3000
- Database is set up on port 5432
To add new tree click on the blue button on the left side menu at the bottom. Tree name must be unique. To delete the tree click on the trashcan icon on the list next to the tree's name. Tree will be generated with a root node which cannot be deleted or editted. You can add a node to the tree by double-clicking on the node that you want to be a parent to the new node. You can also delete or edit node in the same menu. Deleting the node will delete the children nodes too.
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

## Screenshots

![Zrzut ekranu (14)](https://github.com/rychu-tech/trees/assets/61971646/5c75d5cf-c7c7-4b05-ae77-c54c6069954e)
![Zrzut ekranu (15)](https://github.com/rychu-tech/trees/assets/61971646/ce151ffa-56c1-44bc-bfa9-f089c81eb5aa)
![Zrzut ekranu (16)](https://github.com/rychu-tech/trees/assets/61971646/32ed9342-4b84-4712-96a3-f604e08bb155)
![Zrzut ekranu (17)](https://github.com/rychu-tech/trees/assets/61971646/e7241f49-6284-4f2b-aa90-8d409cf299ce)
![Zrzut ekranu (18)](https://github.com/rychu-tech/trees/assets/61971646/1c7fe93d-5fec-4cae-ad40-55a97443f424)
