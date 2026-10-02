# Mini-Bank-App

## ¿Qué es MiniBank?

MiniBank es un banco digital pequeño. Permite que una persona se registre como cliente, abra sus cuentas y maneje su dinero desde la aplicación, sin ir a una agencia.

Con MiniBank un cliente puede:

- **Registrarse** con su documento de identidad, nombre, correo y teléfono.
- **Ingresar a la app** con su usuario y contraseña. Si se equivoca varias veces seguidas, su acceso se bloquea para protegerlo.
- **Tener una o varias cuentas** en soles o en dólares, cada una con su número de cuenta y su CCI.
- **Transferir dinero** a otras cuentas de MiniBank o de otros bancos.
- **Guardar beneficiarios**, es decir, las cuentas a las que envía dinero seguido, para no escribir los datos cada vez.
- **Ver sus movimientos**: cada ingreso y cada salida de dinero queda registrado con el saldo que quedó después.

El proyecto está hecho con **Spring Boot** y sigue una **arquitectura hexagonal**.

## Modelo de datos (ERD)

El script de la base de datos está en [init.sql](init.sql).

```mermaid
erDiagram
    CLIENTE ||--|| USUARIO : "tiene"
    CLIENTE ||--o{ CUENTA : "es dueño de"
    CLIENTE ||--o{ FAVORITOS : "registra"
    CUENTA ||--o{ MOVIMIENTO : "registra"
    CUENTA ||--o{ TRANSFERENCIA : "envía (origen)"
    CUENTA |o--o{ TRANSFERENCIA : "recibe (destino)"
    TRANSFERENCIA ||--o{ MOVIMIENTO : "genera"

    CLIENTE {
        int id PK
        varchar tipo_documento
        varchar numero_documento UK
        varchar nombres
        varchar apellidos
        varchar email UK
        varchar telefono
        varchar estado
        timestamp fecha_registro
    }

    USUARIO {
        int id PK
        varchar username UK
        varchar password_hash
        varchar rol
        int intentos_fallidos
        boolean bloqueado
        timestamp ultimo_login
        int cliente_id FK,UK
    }

    CUENTA {
        int id PK
        varchar numero_cuenta UK
        varchar cci UK
        varchar tipo
        varchar moneda
        decimal saldo
        varchar estado
        timestamp fecha_apertura
        int cliente_id FK
    }

    TRANSFERENCIA {
        int id PK
        decimal monto
        varchar moneda
        varchar estado
        varchar motivo_rechazo
        timestamp fecha_solicitud
        timestamp fecha_proceso
        varchar tipo
        varchar cci_destino
        varchar banco_destino
        varchar titular_destino
        int cuenta_origen_id FK
        int cuenta_destino_id FK
    }

    MOVIMIENTO {
        int id PK
        varchar tipo
        decimal monto
        decimal saldo_resultante
        timestamp fecha
        int cuenta_id FK
        int transferencia_id FK
    }

    FAVORITOS {
        int id PK
        varchar alias
        varchar numero_cuenta
        varchar banco
        varchar titular
        int cliente_id FK
    }
```

## Casos de uso

### Caso de uso 1: Guardar a alguien como favorito

**Quién lo usa:** un cliente de MiniBank.

**Qué quiere lograr:** guardar los datos de una persona a la que le envía dinero seguido para no escribirlos cada vez.

**Cómo sucede:**

1. El cliente entra a la app.
2. Va a la sección "Mis favoritos" y elige "Agregar favorito".
3. Escribe los datos de la otra persona: número de cuenta, banco y nombre del titular.
4. Le pone un nombre corto para reconocerlo fácil, por ejemplo "Mamá" o "Alquiler".
5. Confirma y el favorito queda guardado.
6. La próxima vez que quiera transferir, solo lo escoge de su lista.

**Qué puede salir mal:**

- **Faltan datos o están mal escritos:** la app le dice qué debe corregir antes de guardar.
- **Ya tiene guardada esa misma cuenta:** la app le avisa que ese favorito ya existe.
- **Repite un nombre corto que ya usó:** la app le pide que ponga otro para no confundirse.

----

### Caso de uso 2: Obtener favoritos

**Quién lo usa:** un cliente de MiniBank.

**Qué quiere lograr:** Quiere buscar a las personas que tienen guardadas como favoritos.

**Cómo sucede:**

1. El cliente entra a la app.
2. Va a la sección "Mis favoritos"
3. Le aparecen los favoritos

**Qué puede salir mal:**

- **Que no hayan favoritos guardados:** la app le mostrara que no hay favoritos guardados.
----
