CREATE TABLE cliente
(
  id INT NOT NULL,
  tipo_documento VARCHAR(10) NOT NULL,
  numero_documento VARCHAR(20) NOT NULL,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  telefono VARCHAR(20),
  estado VARCHAR(20) NOT NULL,
  fecha_registro TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (numero_documento),
  UNIQUE (email)
);

CREATE TABLE usuario
(
  id INT NOT NULL,
  username VARCHAR(50) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  rol VARCHAR(20) NOT NULL,
  intentos_fallidos INT NOT NULL,
  bloqueado BOOLEAN NOT NULL,
  ultimo_login TIMESTAMP,
  cliente_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  UNIQUE (cliente_id),
  UNIQUE (username)
);

CREATE TABLE cuenta
(
  id INT NOT NULL,
  numero_cuenta VARCHAR(20) NOT NULL,
  cci VARCHAR(20) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  moneda VARCHAR(3) NOT NULL,
  saldo DECIMAL(15,2) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  fecha_apertura TIMESTAMP NOT NULL,
  cliente_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  UNIQUE (numero_cuenta),
  UNIQUE (cci)
);

CREATE TABLE transferencia
(
  id INT NOT NULL,
  monto DECIMAL(15,2) NOT NULL,
  moneda VARCHAR(3) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  motivo_rechazo VARCHAR(255),
  fecha_solicitud TIMESTAMP NOT NULL,
  fecha_proceso TIMESTAMP,
  tipo VARCHAR(20) NOT NULL,
  cci_destino VARCHAR(20) NOT NULL,
  banco_destino VARCHAR(50) NOT NULL,
  titular_destino VARCHAR(100) NOT NULL,
  cuenta_origen_id INT NOT NULL,
  cuenta_destino_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (cuenta_origen_id) REFERENCES cuenta(id),
  FOREIGN KEY (cuenta_destino_id) REFERENCES cuenta(id)
);

CREATE TABLE movimiento
(
  id INT NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  monto DECIMAL(15,2) NOT NULL,
  saldo_resultante DECIMAL(15,2) NOT NULL,
  fecha TIMESTAMP NOT NULL,
  cuenta_id INT NOT NULL,
  transferencia_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cuenta_id) REFERENCES cuenta(id),
  FOREIGN KEY (transferencia_id) REFERENCES transferencia(id)
);

CREATE TABLE favoritos
(
  id INT NOT NULL,
  alias VARCHAR(50) NOT NULL,
  numero_cuenta VARCHAR(20) NOT NULL,
  banco VARCHAR(50) NOT NULL,
  titular VARCHAR(100) NOT NULL,
  cliente_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  UNIQUE (cliente_id, numero_cuenta, banco),
  UNIQUE (cliente_id, alias)
);