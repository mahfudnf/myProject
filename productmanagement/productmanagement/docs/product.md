# Product API Spec

## Create Product
Endpoint : POST /api/products

Request Body :

- kode id F : Food
- kode id D : Drink

```json
{
  "id" : "F/D + random string",
  "name": "bakso",
  "price" : "10000",
  "stock" : "10"
}
```

Response Body(Success) :

```json
{
  "data" : "OK"
}
```

Response Body(Failed) :

```json
{
  "errors" : "the product already exists"
}
```

## Get Product

Endpoint : GET /api/products/{id}

Response Body(Success) :

```json
{
  "data" : {
    "id": "F/D + random string",
    "name": "bakso",
    "price": "10000",
    "stock": "10",
    "category": "food",
    "status": "active",
    "create at": "10022026" //datecreateat
  }
}
```

Response Body(Failed) :

```json
{
  "errors" : "product not found"
}
```

## Search Product

Endpoint : GET /api/products/

Query Param :

- name : String, name, using Like query, optional
- category : String, category, using Like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Response Body(Success) :

```json
{
  "data" : [
    {
      "id": "F/D + random string",
      "name": "bakso",
      "price": "10000",
      "stock": "10",
      "category": "food",
      "status": "active",
      "create at": "10022026" //datecreateat
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body(Failed) :

```json
{
  "errors" : "product not found"
}
```

## Update Product

Endpoint : PATCH /api/products/{id}

Request Body :

```json
 {
  "name" : "seblak", //put if only one to update name
  "price" : "15000", //put if only one to update price
  "stock" : "20" //put if only one to update stock
}
```

Response Body(Success) :

```json
 {
  "data" : {
    "name" : "seblak",
    "price" : "15000",
    "stock" : "20"
  }
}
```

Response Body(Failed) :

```json
 {
  "errors" : "product not found"
}
```

## Remove Product

Endpoint : DELETE /api/products/{id}

Rsponse Body(Success) :

```json
 {
  "data" : "OK"
}
```

Response Body(Failed) :

```json
 {
  "errors" : "product not found"
}
```

