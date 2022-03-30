# Projeto Selic

> Aplicação que faz operações de CRUD na API taxa Selic

Este projeto realiza as operações de CRUD em uma API do Banco Central onde constam os valores mensais da taxa selic dos anos de 1986 a 2021.
Estes dados são armazenados em um banco de dados H2.
---
## 👩‍💻 Meta

Desenvolvido por Karin de Oliveira 



---
# 🖥️ Índice

- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como rodar na sua máquina](#-como-baixar-o-projeto)
- [Histórico de Atualizações](#-Histórico-de-Atualizações)
- [Documentação da API](#-Documentação-da-API)
- [Contatos](#-Meta)
---
## 🚀 Tecnologias utilizadas

O projeto foi desenvolvido utilizando:

- JDK 11
- Maven
- Spring Boot
- JPA & Hibernate
- Swagger
- Intellij Idea
- Postman

---

## 🧑‍💻 Como rodar na sua máquina

- Clone o projeto que consta nesse repositório No terminal rode o seguinte comando:
```sh
mvn clean install
```

- Na sua IDE de preferência, vá até o pacote/diretório br.com.ibm


- Rode a classe main do projeto JavaSelicApplication


- Para testar os endpoints utilize a documentação como base. 
Acesse [Swagger](http://localhost:8000/swagger-ui.html)
ou [aqui](http://localhost:8000/swagger-ui.html)


- Para acessar a URL base do sistema, clique [aqui](http://localhost:8000/swagger-ui.html)


- Para testar o banco de dados em memória - H2 database - utilize os seguintes dados: 

> JDBCURL: jdbc:h2:mem:app

> SENHA:

>USERNAME: sa

> http://localhost:8000/h2

---
## 💾 Histórico de Atualizações

*V9
-README

*V8
-JavaSelicAplicationTest

*V7
-SellicControllerTest - Post

*V6
-Swagger

*V5
-@DeleteMapping (por id)

*V4
-@PatchMapping

*V3
-@GetMapping (por ano e All)

*V2
-Estrutura básica, @PostMapping

*V1
-Initial commit

---
## 👓 Documentação da API 

```sh
---
swagger: '2.0'
info:
  description: '"Desenvolvido por - Karin de Oliveira"'
  version: 1.0.0
  title: 'Projeto Selic - Java, Spring, H2 '
  license:
    name: Apache License Version 2.0
    url: https://www.apache.org/licenses/LICENSE-2.0"
host: localhost:8080
basePath: "/"
tags:
- name: basic-error-controller
  description: Basic Error Controller
- name: selic-controller
  description: Selic Controller
paths:
  "/error":
    get:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingGET
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not Found
      deprecated: false
    head:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingHEAD
      consumes:
      - application/json
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '204':
          description: No Content
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
      deprecated: false
    post:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingPOST
      consumes:
      - application/json
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '201':
          description: Created
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not Found
      deprecated: false
    put:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingPUT
      consumes:
      - application/json
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '201':
          description: Created
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not Found
      deprecated: false
    delete:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingDELETE
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '204':
          description: No Content
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
      deprecated: false
    options:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingOPTIONS
      consumes:
      - application/json
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '204':
          description: No Content
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
      deprecated: false
    patch:
      tags:
      - basic-error-controller
      summary: errorHtml
      operationId: errorHtmlUsingPATCH
      consumes:
      - application/json
      produces:
      - text/html
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/ModelAndView"
        '204':
          description: No Content
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
      deprecated: false
  "/v1/selic/atualizarselic":
    patch:
      tags:
      - selic-controller
      summary: Este request atualiza os dados de mês, ano e valor
      operationId: atualizarUsingPATCH
      consumes:
      - application/json
      produces:
      - "*/*"
      parameters:
      - name: ano
        in: query
        description: ano
        required: true
        type: integer
        format: int32
      - name: mes
        in: query
        description: mes
        required: true
        type: integer
        format: int32
      - name: valor
        in: query
        description: valor
        required: true
        type: number
        format: double
      responses:
        '200':
          description: OK
          schema:
            "$ref": "#/definitions/SelicEntity"
        '204':
          description: No Content
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
      deprecated: false
  "/v1/selic/deletar":
    delete:
      tags:
      - selic-controller
      summary: Este request deleta os valores mês, ano e valor daquele código serie
        específico.
      operationId: deleteSelicUsingDELETE
      produces:
      - "*/*"
      parameters:
      - name: codigo_Serie
        in: query
        description: codigo_Serie
        required: true
        type: integer
        format: int32
      - name: flashAttributes
        in: query
        required: false
        type: object
      responses:
        '200':
          description: OK
          schema:
            type: string
        '204':
          description: No Content
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
      deprecated: false
  "/v1/selic/save":
    post:
      tags:
      - selic-controller
      summary: Este request lê os dados de datas e valores das taxas Selic. Também
        gera um banco de dados no repositório H2.
      operationId: salvarSelicUsingPOST
      consumes:
      - application/json
      produces:
      - "*/*"
      responses:
        '201':
          description: Created
          schema:
            type: array
            items:
              "$ref": "#/definitions/SelicRequest"
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not Found
      deprecated: false
  "/v1/selic/selic":
    get:
      tags:
      - selic-controller
      summary: Este request retorna todos os dados contidos na API(mês, ano e valor)
      operationId: buscaSelicUsingGET
      produces:
      - "*/*"
      responses:
        '200':
          description: OK
          schema:
            type: array
            items:
              "$ref": "#/definitions/SelicEntity"
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not Found
      deprecated: false
  "/v1/selic/{ano}":
    get:
      tags:
      - selic-controller
      summary: Este request retorna os valores da taxa Selic por ano.
      operationId: getSelicByYearUsingGET
      produces:
      - "*/*"
      parameters:
      - name: ano
        in: path
        description: ano
        required: true
        type: integer
        format: int32
      responses:
        '200':
          description: OK
          schema:
            type: number
            format: double
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not Found
      deprecated: false
definitions:
  ModelAndView:
    type: object
    properties:
      empty:
        type: boolean
      model:
        type: object
      modelMap:
        type: object
        additionalProperties:
          type: object
      reference:
        type: boolean
      status:
        type: string
        enum:
        - 100 CONTINUE
        - 101 SWITCHING_PROTOCOLS
        - 102 PROCESSING
        - 103 CHECKPOINT
        - 200 OK
        - 201 CREATED
        - 202 ACCEPTED
        - 203 NON_AUTHORITATIVE_INFORMATION
        - 204 NO_CONTENT
        - 205 RESET_CONTENT
        - 206 PARTIAL_CONTENT
        - 207 MULTI_STATUS
        - 208 ALREADY_REPORTED
        - 226 IM_USED
        - 300 MULTIPLE_CHOICES
        - 301 MOVED_PERMANENTLY
        - 302 FOUND
        - 302 MOVED_TEMPORARILY
        - 303 SEE_OTHER
        - 304 NOT_MODIFIED
        - 305 USE_PROXY
        - 307 TEMPORARY_REDIRECT
        - 308 PERMANENT_REDIRECT
        - 400 BAD_REQUEST
        - 401 UNAUTHORIZED
        - 402 PAYMENT_REQUIRED
        - 403 FORBIDDEN
        - 404 NOT_FOUND
        - 405 METHOD_NOT_ALLOWED
        - 406 NOT_ACCEPTABLE
        - 407 PROXY_AUTHENTICATION_REQUIRED
        - 408 REQUEST_TIMEOUT
        - 409 CONFLICT
        - 410 GONE
        - 411 LENGTH_REQUIRED
        - 412 PRECONDITION_FAILED
        - 413 PAYLOAD_TOO_LARGE
        - 413 REQUEST_ENTITY_TOO_LARGE
        - 414 URI_TOO_LONG
        - 414 REQUEST_URI_TOO_LONG
        - 415 UNSUPPORTED_MEDIA_TYPE
        - 416 REQUESTED_RANGE_NOT_SATISFIABLE
        - 417 EXPECTATION_FAILED
        - 418 I_AM_A_TEAPOT
        - 419 INSUFFICIENT_SPACE_ON_RESOURCE
        - 420 METHOD_FAILURE
        - 421 DESTINATION_LOCKED
        - 422 UNPROCESSABLE_ENTITY
        - 423 LOCKED
        - 424 FAILED_DEPENDENCY
        - 425 TOO_EARLY
        - 426 UPGRADE_REQUIRED
        - 428 PRECONDITION_REQUIRED
        - 429 TOO_MANY_REQUESTS
        - 431 REQUEST_HEADER_FIELDS_TOO_LARGE
        - 451 UNAVAILABLE_FOR_LEGAL_REASONS
        - 500 INTERNAL_SERVER_ERROR
        - 501 NOT_IMPLEMENTED
        - 502 BAD_GATEWAY
        - 503 SERVICE_UNAVAILABLE
        - 504 GATEWAY_TIMEOUT
        - 505 HTTP_VERSION_NOT_SUPPORTED
        - 506 VARIANT_ALSO_NEGOTIATES
        - 507 INSUFFICIENT_STORAGE
        - 508 LOOP_DETECTED
        - 509 BANDWIDTH_LIMIT_EXCEEDED
        - 510 NOT_EXTENDED
        - 511 NETWORK_AUTHENTICATION_REQUIRED
      view:
        "$ref": "#/definitions/View"
      viewName:
        type: string
    title: ModelAndView
  SelicEntity:
    type: object
    properties:
      codigo_Serie:
        type: integer
        format: int64
      data:
        type: string
        format: date
      valor:
        type: number
        format: double
    title: SelicEntity
  SelicRequest:
    type: object
    properties:
      data:
        type: string
      valor:
        type: number
        format: double
    title: SelicRequest
  View:
    type: object
    properties:
      contentType:
        type: string
    title: View
```



