# -LCIII-FINAL-06022024 -- # API REST de Sorteo de Loteria

## Contexto del Negocio:
Es el encargado de diseñar un sistema para la carga de apuestas y control de premios a entregar a los ganadores según un determinado criterio de valoración.


## Enpoint a diseñar

### CRUD sorteo - (10 pts)
- Endpoint: ```loteria/sorteos```
```
{
  "id_sorteo": 123,
  "fecha_sorteo": "2023-01-16",
  "totalEnReserva": 1000000,
  "numerosSorteados": [
    { 1, 56789},
    {2, 10130},
    {3, 15081},
    {4, 20442},
    {5, 25973}
  ]
}
```

### Búsqueda de números sorteados por fecha:  (10 pts)
Se deberá consultar los numeros ganadores del sorteo correspondiente a la fecha ingresada

- Endpoint: ```loteria/sorteos?fecha=2024-01-16```
- Método: ```GET```
- Respuesta:
```
{
  "id_sorteo": 123,
  "fecha_sorteo": "2023-01-16",
  "totalEnReserva": 1000000,
  "numerosSorteados": [
        { 1, 56789},
        {2, 10130},
        {3, 15081},
        {4, 20442},
        {5, 25973}
    ]
}
```

### Cantidad de ganadores por numero sorteado:  (10 pts)
- Endpoint: ```loteria/sorteos/ganadores/{id_sorteo}```
- Método: ```GET```
- Respuesta:
```
{
  "id_sorteo": 123,
  "fecha_sorteo": "2023-01-16",
  "totalGanadores": 1,
  "totalPremiosEntregados": 350000
}
```
---

### CRUD apuesta (10 pts)

- Endpoint: ```loteria/apuesta```
```
{
    "fecha_sorteo": "2024-12-25",
    "id_cliente": "Lorem Ipsum",
    "numero": "56789",
    "montoApostado": 100
}
```
- Respuesta:
```
  {
    "id_sorteo": 123,
    "fecha_sorteo": "2024-12-25",
    "id_cliente": "Lorem Ipsum",
    "numero": "9876",
    "resultado": "GANADOR"
  }
```

### Recuperar información general solamente de apuestas ganadas (10 pts)

- Endpoint: ```loteria/ganador/{id_sorteo}```
- Método: ```GET```
- Respuesta:
```
  {
    "id_sorteo": 123,
    "fecha_sorteo": "2024-12-25",
    "totalDeApuestas": 100,
    "totalPagado": 350000,
    "totalEnReserva": 650100
  }
```
### Premios a pagar segun cantidad de asiertos en terminación del numero (5 pts)
>Tomando el endpoint anterior (loteria/ganador/{id_sorteo}) calcular el campo **"totalPagado"** con la siguiente tabla:
- 2 cifras: 700 %
- 3 cifras: 7000 %
- 4 cifras: 60000 %
- 5 cifras: 350000 %

### Total acumulado en reserva (5 pts)
>Tomando el endpoint anterior (loteria/ganador/{id_sorteo}) calcular el campo **"totalEnReserva"** como la resultante de la sumatoria de las apuestas realizadas, más la reserva existente y la posterior quita de los premios otorgados.

---
### Testing (40 pts)
- Es obligatorio la creación de testing unitario sobre la lógica de negocio de todo el proyecto
