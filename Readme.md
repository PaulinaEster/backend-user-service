# Como rodar a aplicação mongo-java 
### Link tutorial: <https://www.youtube.com/watch?v=QumOvEDMQ34&t=1827s>
- Clone esse repositorio
- Rode o arquivo ApplicationLivros do package com.paulina.mongoJava
- abra o terminal na pasta do projeto e rode: *docker-compose up* 
- depois rode: *docker exec -it mongojava_mongodb_1 bash*
- depois rode: *mongo*
- depois rode: *show dbs*  **isso vai mostrar os dbs**
- pronto seu db ta funcionando

Voce pode testar usando a funcao **generated requests in HTTP Client** na funcao Post do seu 
Controller, passando um corpo como requisição no arquivo generated-requests.http:
```
###
POST http://localhost:8080/livros
Content-Type: application/json

{
  "id": "1",
  "name": "As aventuras",
  "author": "J.K",
  "isbn": "12342"
}
```
Depois disso abra no link: **http://localhost:8080/livros** e veja se json mandado esta la.
Se estiver esta funcionando!! 


##Para verificar a conexao com o mongo
- abra na lateral direita do InteliJ a aba *Database*;
- clique no simbolo '+' e depois em **Data soure from URL**;
- coloque a url: *mongodb://localhost:20017 e clique em "ok";

  - Vai abrir uma janela maior, clique em **Dowload missing driver files** depois em **Test Connection**  e espere dar um ok;
  - Se nao tiver a opcação **Dowload missing driver files** ou clique em:
  - **Test Connection** e espere dar um ok.
    - vai abrir uma aba de console
