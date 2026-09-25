CREATE TABLE cliente
(
  id VARCHAR(BIGINT) NOT NULL,
  tipo_documento VARCHAR(10) NOT NULL,
  numero_documento VARCHAR(20) NOT NULL,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  telefono VARCHAR(20),
  estado VARCHAR(20) NOT NULL,
  fecha_registro VARCHAR(TIMESTAMP) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (numero_documento),
  UNIQUE (email)
);

CREATE TABLE usuario
(
  id VARCHAR(BIGINT) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  rol VARCHAR(20) NOT NULL,
  intentos_fallidos INT NOT NULL,
  bloqueado VARCHAR(BOOLEAN) NOT NULL,
  ultimo_login VARCHAR(TIMESTAMP),
  cliente_id VARCHAR NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  UNIQUE (cliente_id),
  UNIQUE (username)
);

CREATE TABLE cuenta
(
  id VARCHAR(BIGINT) NOT NULL,
  numero_cuenta VARCHAR(20) NOT NULL,
  cci VARCHAR(20) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  moneda VARCHAR(3) NOT NULL,
  saldo VARCHAR(DECIMAL(15,2)) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  fecha_apertura VARCHAR(TIMESTAMP) NOT NULL,
  cliente_id VARCHAR NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  UNIQUE (numero_cuenta),
  UNIQUE (cci)
);

CREATE TABLE transferencia
(
  id VARCHAR(BIGINT) NOT NULL,
  monto VARCHAR(DECIMAL(15,2)) NOT NULL,
  moneda VARCHAR(3) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  motivo_rechazo VARCHAR(255),
  fecha_solicitud VARCHAR(TIMESTAMP) NOT NULL,
  fecha_proceso VARCHAR(TIMESTAMP),
  tipo VARCHAR(20) NOT NULL,
  cci_destino INT NOT NULL,
  banco_destino INT NOT NULL,
  titular_destino INT NOT NULL,
  cuenta_origen_id VARCHAR NOT NULL,
  cuenta_destino_id VARCHAR NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cuenta_origen_id) REFERENCES cuenta(id),
  FOREIGN KEY (cuenta_destino_id) REFERENCES cuenta(id)
);

CREATE TABLE movimiento
(
  id VARCHAR(BIGINT) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  monto VARCHAR(DECIMAL(15,2)) NOT NULL,
  saldo_resultante VARCHAR(DECIMAL(15,2)) NOT NULL,
  fecha VARCHAR(TIMESTAMP) NOT NULL,
  cuenta_id VARCHAR NOT NULL,
  transferencia_id VARCHAR NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cuenta_id) REFERENCES cuenta(id),
  FOREIGN KEY (transferencia_id) REFERENCES transferencia(id)
);

CREATE TABLE beneficiario
(
  id VARCHAR(BIGINT) NOT NULL,
  alias VARCHAR(50) NOT NULL,
  numero_cuenta VARCHAR(20) NOT NULL,
  banco VARCHAR(50) NOT NULL,
  titular VARCHAR(TIMESTAMP) NOT NULL,
  New_Column INT NOT NULL,
  cliente_id VARCHAR NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);