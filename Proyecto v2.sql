CREATE TABLE hotel(
    id_hotel INT PRIMARY KEY,
    nombre VARCHAR2(50),
    direccion VARCHAR2(100),
    telefono VARCHAR2(20)
);

CREATE TABLE Restaurante (
    id_restaurante INT PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,                  
    tipo_cocina VARCHAR(50) NOT NULL,             
    capacidad INT NOT NULL,                                      
    id_hotel INT NOT NULL,                         
    FOREIGN KEY (id_hotel) REFERENCES hotel(id_hotel)
);    

CREATE TABLE proveedor (
    id_proveedor INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    email VARCHAR(100),
    id_hotel INT,
    FOREIGN KEY (id_hotel) REFERENCES hotel(id_hotel)
);

CREATE TABLE suministro (
    id_suministro INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    cantidad INT CHECK (cantidad >= 0),
    precio_unitario DECIMAL(10, 2),
    id_proveedor INT,
    id_hotel INT,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_hotel) REFERENCES hotel(id_hotel)
);


CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50),
    email VARCHAR(100),
    telefono VARCHAR(15),
    direccion VARCHAR(255)
);

CREATE TABLE habitacion (
    id_habitacion INT PRIMARY KEY,
    tipo_habitacion VARCHAR(50),
    precio DECIMAL(10, 2),
    capacidad INT,
   disponibilidad NUMBER(1) -- 0 para no disponible, 1 para disponible
);

CREATE TABLE reserva (
    id_reserva INT PRIMARY KEY,
    id_cliente INT,
    id_hotel INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado_reserva VARCHAR(50),
    id_habitacion INT,
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_hotel) REFERENCES hotel(id_hotel)
);

CREATE TABLE empleado (
    id_empleado INT PRIMARY KEY,
    id_puesto INT,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    fecha_contratacion DATE,
    FOREIGN KEY (id_puesto) REFERENCES puesto(id_puesto)
);


CREATE TABLE puesto (
    id_puesto INT PRIMARY KEY,
    salario DECIMAL(10,2),
    descripcion VARCHAR2(100)
);


CREATE TABLE servicio_adicional (
    id_servicio INT PRIMARY KEY,
    descripcion VARCHAR(255),
    fecha DATE,
    precio DECIMAL(10,2),
    id_reserva INT,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

CREATE TABLE factura (
    id_factura INT PRIMARY KEY,
    id_reserva INT,
    fecha_emision DATE,
    monto_total DECIMAL(10, 2),
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

CREATE TABLE detalle_factura (
    id_detalle_factura INT PRIMARY KEY,
    id_factura INT,
    id_servicio INT,
    cantidad INT,
    precio_unitario DECIMAL(10,2),
    FOREIGN KEY (id_factura) REFERENCES factura(id_factura),
    FOREIGN KEY (id_servicio) REFERENCES servicio_adicional(id_servicio)
);

CREATE TABLE comentario (
    id_comentario INT PRIMARY KEY,
    id_reserva INT,
    calificacion INT,
    comentario VARCHAR(50),
    fecha_comentario DATE,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);

CREATE TABLE pago (
    id_pago INT PRIMARY KEY,
    id_reserva INT,
    monto DECIMAL(10, 2),
    fecha_pago DATE,
    metodo_pago VARCHAR(50),
    id_factura INT, 
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva)
);


---------------------------  INSERCION DE LOS DATOS ----------------------------------

--HOTEL
INSERT INTO hotel 
    (id_hotel, nombre, direccion, telefono) 
VALUES (1, 'Hotel Altavista - Tarbaca', 'Tarbaca, Aserri, 200 mts', '22304941');

--Cliente 
INSERT INTO cliente (id_cliente, nombre, apellido1, apellido2, email, telefono, direccion) 
VALUES (1, 'Marco', 'Perez', 'Rodriguez', 'juan.perez@gmail.com', '25405657', 'San Rafael de Escazu');

-- Habitacion
INSERT INTO habitacion (id_habitacion, tipo_habitacion, precio, capacidad, disponibilidad) 
VALUES (1, 'Suite', 150.00, 2, 1);

--Puesto 
INSERT INTO puesto (id_puesto, salario, descripcion) 
VALUES (1, 2500.00, 'Recepcionista');

--Restaurante
INSERT INTO restaurante (id_restaurante, nombre, tipo_cocina, capacidad, id_hotel) 
VALUES (1, 'Restaurante Pura vida', 'Comida Tipca de Costa Rica', 50, 1);

--Proveedor
INSERT INTO proveedor (id_proveedor, nombre, telefono, direccion, email, id_hotel) 
VALUES (1, 'Suministros Sol.', '24563512', 'San Jose, Merced, Coca cola', 'sol@suministros.com', 1);

--Suministro 
INSERT INTO suministro (id_suministro, nombre, descripcion, cantidad, precio_unitario, id_proveedor, id_hotel) 
VALUES (1, 'S醔anas', 'S醔anas de algod髇 100%', 100, 4000, 1, 1);

--Empleado
INSERT INTO empleado (id_empleado, id_puesto, nombre, apellido, fecha_contratacion) 
VALUES (1, 1, 'Maria', 'Gonzalez', TO_DATE('2023-01-15', 'YYYY-MM-DD'));


--Reserva
INSERT INTO reserva (id_reserva, id_cliente, id_hotel, fecha_inicio, fecha_fin, estado_reserva, id_habitacion) 
VALUES (1, 1, 1, TO_DATE('2024-12-01', 'YYYY-MM-DD'), TO_DATE('2024-12-05','YYYY-MM-DD'), 'Confirmada', 1);

--Factura
INSERT INTO factura (id_factura, id_reserva, fecha_emision, monto_total) 
VALUES (1, 1, TO_DATE('2024-12-01','YYYY-MM-DD'), 150000);

--Servicio Adicional
INSERT INTO servicio_adicional (id_servicio, descripcion, fecha, precio, id_reserva) 
VALUES (1, 'Spa', TO_DATE('2024-12-02', 'YYYY-MM-DD'), 10000, 1);

--Detalle Factura
INSERT INTO detalle_factura (id_detalle_factura, id_factura, id_servicio, cantidad, precio_unitario) 
VALUES (1, 1, 1, 1, 50);

--Comentario
INSERT INTO comentario (id_comentario, id_reserva, calificacion, comentario, fecha_comentario) 
VALUES (1,1, 5, 'Excelente servicio', TO_DATE('2024-12-06','YYYY-MM-DD'));

--Pago
INSERT INTO pago (id_pago, id_reserva, monto, fecha_pago, metodo_pago, id_factura) 
VALUES (1, 1, 160000, TO_DATE('2024-12-01','YYYY-MM-DD'), 'Tarjeta de Cr閐ito', 1);


CREATE TABLE pruba(id INT);

--------------------------------------------------------------------------------------

--1er Procedimiento Almacenado (SP)

--1. Insertar un Cliente nuevo

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE agregarCliente(c_id_cliente IN INT, c_nombre IN VARCHAR2, 
c_apellido1 IN VARCHAR2, c_apellido2 IN VARCHAR2, c_email IN VARCHAR2, 
c_telefono IN VARCHAR2, c_direccion IN VARCHAR2)
AS
    v_contador INT;
BEGIN
    SELECT COUNT(*) INTO v_contador
    FROM CLIENTE
    WHERE id_cliente = c_id_cliente;
    
    IF v_contador > 0 THEN
        DBMS_OUTPUT.PUT_LINE('El Cliente con ID: '|| c_id_cliente || ' ya existe, cambie el valor');
    ELSE
        INSERT INTO CLIENTE(id_cliente,nombre,apellido1,apellido2,email,telefono,direccion)
        VALUES(c_id_cliente, c_nombre, c_apellido1, c_apellido2, c_email, c_telefono, c_direccion);
    
            DBMS_OUTPUT.PUT_LINE('Nuevo Cliente agregado correctamente: '|| c_nombre || ' ' || c_apellido1 || ' ' || c_apellido2);
    END IF;
EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Se ha producido un error en los datos');
END;

--Ejecucion SP Agregar Cliente
EXEC agregarCliente(3, 'Ejemplo','Mora','Mora','Ej@gmail.com','22493041','Calle Leo, frente a Escuela G');
--Cambiar este exec para agregar cualquier cliente, si no cambia el ID, sale error
SELECT * FROM CLIENTE;
-----------------------------------------------------------------------------

--2do SP

--2. Actualizar un Cliente Existente

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE actualizarClienteExistente(c_id_cliente IN INT, c_nombre IN VARCHAR2,
c_apellido1 IN VARCHAR2, c_apellido2 IN VARCHAR2, c_email IN VARCHAR2, c_telefono IN VARCHAR2,
c_direccion IN VARCHAR2)
AS
    v_verificar INT;--verificacion del ID
BEGIN

    --Verificar el ID, si no existe salta error
    SELECT id_cliente INTO v_verificar
    FROM CLIENTE
    WHERE id_cliente = c_id_cliente;
    
    UPDATE CLIENTE
    SET nombre = c_nombre,
    apellido1 = c_apellido1,
    apellido2 = c_apellido2,
    email = c_email,
    telefono = c_telefono,
    direccion = c_direccion
    WHERE id_cliente = c_id_cliente;
    
    DBMS_OUTPUT.PUT_LINE('Cliente actualizado correctamente: '|| c_nombre || ' ' || c_apellido1 || ' ' || c_apellido2);
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error, el Cliente con ID: '|| c_id_cliente || ' no existe');
END;

--Ejecucion SP Actualizacion Cliente Existente
--En este caso se cambia el email
EXEC actualizarClienteExistente(1, 'Pedro','Robles','L贸pez','PedroRL@gmail.com','22493030','Calle L贸pez frente a Escuela Hern谩ndez');

SELECT * FROM CLIENTE;
-----------------------------------------------------------------------------

--3er SP

--3. Eliminar un Cliente

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE eliminarCliente(c_id_cliente IN INT)
AS
    v_verificarE INT;--Verificar el ID para eliminar
BEGIN
    SELECT id_cliente INTO v_verificarE
    FROM CLIENTE
    WHERE id_cliente = c_id_cliente;
        
    DELETE FROM CLIENTE
    WHERE id_cliente = c_id_cliente;
    
        DBMS_OUTPUT.PUT_LINE('Eliminaci贸n de Cliente correctamente, el ID es: ' || c_id_cliente);

EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Se ha producido un error en los datos');  
END;

--Ejecucion SP Eliminar Cliente
EXEC eliminarCliente(3);

SELECT * FROM CLIENTE;
-----------------------------------------------------------------------------

--4to SP

--4. Agregar un Empleado

CREATE OR REPLACE PROCEDURE agregarEmpleado(e_id_empleado IN INT, e_nombre IN VARCHAR2, 
e_apellido IN VARCHAR2, e_fecha_contratacion IN DATE)
AS
    v_contadorE INT;
BEGIN
    SELECT COUNT(*) INTO v_contadorE
    FROM EMPLEADO
    WHERE id_empleado = e_id_empleado;
    
    IF v_contadorE > 0 THEN
        DBMS_OUTPUT.PUT_LINE('El Empleado con ID: '|| e_id_empleado || ' ya existe, cambie el valor');
    ELSE
        INSERT INTO EMPLEADO(id_empleado,nombre,apellido,fecha_contratacion)
        VALUES(e_id_empleado, e_nombre, e_apellido, e_fecha_contratacion);
    
            DBMS_OUTPUT.PUT_LINE('Nuevo Empleado agregado correctamente: '|| e_nombre || ' ' || e_apellido);
    END IF;
EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Se ha producido un error en los datos');
END;

--Ejecucion SP Agregar Empleado
EXEC agregarEmpleado(4, 'Ejemplo','Torres P茅rez', TO_DATE('2024-11-16', 'YYYY-MM-DD'));
--Cambiar este exec para agregar cualquier empleado, si no cambia el ID, sale error
SELECT * FROM EMPLEADO;
-----------------------------------------------------------------------------

--5to SP

--5. Actualizar Empleado

CREATE OR REPLACE PROCEDURE actualizarEmpleadoExistente(e_id_empleado IN INT, e_nombre IN VARCHAR2, 
e_apellido IN VARCHAR2, e_fecha_contratacion IN DATE)
AS
    v_verificar INT;--verificacion del ID
BEGIN

    --Verificar el ID, si no existe salta error
    SELECT id_empleado INTO v_verificar
    FROM EMPLEADO
    WHERE id_empleado = e_id_empleado;
    
    UPDATE EMPLEADO
    SET nombre = e_nombre,
    apellido = e_apellido,
    fecha_contratacion = e_fecha_contratacion
    WHERE id_empleado = e_id_empleado;
    
    DBMS_OUTPUT.PUT_LINE('Empleado actualizado correctamente: '|| e_nombre || ' ' || e_apellido);
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error, el Empleado con ID: '|| e_id_empleado || ' no existe');
END;

--Ejecucion SP Agregar Cliente Existente
--En este caso se cambia el nombre (Alex a Alexis)
EXEC actualizarEmpleadoExistente(2,'Alexis','Torres Calder贸n', TO_DATE('2024-11-14', 'YYYY-MM-DD'));

SELECT * FROM EMPLEADO;   
-----------------------------------------------------------------------------

--6to SP

--6. Eliminar Empleado

CREATE OR REPLACE PROCEDURE eliminarEmpleado(e_id_empleado IN INT)
AS
    v_verificarE INT;--Verificar el ID para eliminar
BEGIN
    SELECT id_empleado INTO v_verificarE
    FROM EMPLEADO
    WHERE id_empleado = e_id_empleado;
        
    DELETE FROM EMPLEADO
    WHERE id_empleado = e_id_empleado;
    
        DBMS_OUTPUT.PUT_LINE('Eliminaci贸n de Empleado correctamente, el ID es: ' || e_id_empleado);

EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Se ha producido un error en los datos');  
END;

--Ejecucion SP Eliminar Empleado
EXEC eliminarEmpleado(4);

SELECT * FROM EMPLEADO;
-----------------------------------------------------------------------------

--7 SP

-- CONSULTAR LOS EMPLEADOS POR EL PUESTO  

CREATE OR REPLACE PROCEDURE ConsultarEmpleadosPorPuesto(p_id_puesto IN puesto.id_puesto%TYPE)
IS
BEGIN
    FOR r IN (
        SELECT e.id_empleado, e.nombre, e.apellido, e.fecha_contratacion, p.descripcion
        FROM empleado e
        JOIN puesto p ON e.id_puesto = p.id_puesto
        WHERE e.id_puesto = p_id_puesto
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID: ' || r.id_empleado || ', Nombre: ' || r.nombre || ', Apellido: ' || r.apellido || ', Fecha Contratacion: ' || r.fecha_contratacion || ', Puesto: ' || r.descripcion);
    END LOOP;
END ConsultarEmpleadosPorPuesto;

-- EJECUCION
EXECUTE ConsultarEmpleadosPorPuesto(1);

-----------------------------------------------------------------------------

--8 SP

-- Agregar un nuevo hotel 

CREATE OR REPLACE PROCEDURE AgregarHotel( 
    p_id_hotel IN hotel.id_hotel%TYPE,
    p_nombre IN hotel.nombre%TYPE,
    p_direccion IN hotel.direccion%TYPE,
    p_telefono IN hotel.telefono%TYPE
)
IS
    v_count NUMBER; 
BEGIN

    SELECT COUNT(*)
    INTO v_count
    FROM hotel
    WHERE id_hotel = p_id_hotel;

    IF v_count > 0 THEN
        
        DBMS_OUTPUT.PUT_LINE('El ID del hotel ya existe en la tabla.');
    ELSE
        
        INSERT INTO hotel (id_hotel, nombre, direccion, telefono)
        VALUES (p_id_hotel, p_nombre, p_direccion, p_telefono);
    END IF;
END AgregarHotel;


-----------------------------------------------------------------------------

--9 SP

-- Actualizar los datos del hotel

CREATE OR REPLACE PROCEDURE ActualizarHotel(
    p_id_hotel IN hotel.id_hotel%TYPE,
    p_nombre IN hotel.nombre%TYPE,
    p_direccion IN hotel.direccion%TYPE,
    p_telefono IN hotel.telefono%TYPE
)
IS
BEGIN
    UPDATE hotel
    SET nombre = p_nombre, direccion = p_direccion, telefono = p_telefono
    WHERE id_hotel = p_id_hotel;
    
END ActualizarHotel;

-----------------------------------------------------------------------------

--10 SP

-- Eliminar un hotel

CREATE OR REPLACE PROCEDURE EliminarHotel(p_id_hotel IN hotel.id_hotel%TYPE)
IS
BEGIN
    DELETE FROM hotel WHERE id_hotel = p_id_hotel;
END EliminarHotel;

-----------------------------------------------------------------------------

--11 SP

--  Agregar un nuevo proveedor 

CREATE OR REPLACE PROCEDURE AgregarProveedor(
    p_id_proveedor IN proveedor.id_proveedor%TYPE,
    p_nombre IN proveedor.nombre%TYPE,
    p_telefono IN proveedor.telefono%TYPE,
    p_direccion IN proveedor.direccion%TYPE,
    p_email IN proveedor.email%TYPE,
    p_id_hotel IN proveedor.id_hotel%TYPE
)
IS
BEGIN
    INSERT INTO proveedor (id_proveedor, nombre, telefono, direccion, email, id_hotel)
    VALUES (p_id_proveedor, p_nombre, p_telefono, p_direccion, p_email, p_id_hotel);
END AgregarProveedor;
-----------------------------------------------------------------------------

--12 SP

--  Actualizar el proveedor 

CREATE OR REPLACE PROCEDURE ActualizarProveedor(
    p_id_proveedor IN proveedor.id_proveedor%TYPE,
    p_nombre IN proveedor.nombre%TYPE,
    p_telefono IN proveedor.telefono%TYPE,
    p_direccion IN proveedor.direccion%TYPE,
    p_email IN proveedor.email%TYPE,
    p_id_hotel IN proveedor.id_hotel%TYPE
)
IS
BEGIN
    UPDATE proveedor
    SET nombre = p_nombre, telefono = p_telefono, direccion = p_direccion, email = p_email, id_hotel = p_id_hotel
    WHERE id_proveedor = p_id_proveedor;
END ActualizarProveedor;

-----------------------------------------------------------------------------

--13 SP

--  Eliminar el proveedor 

CREATE OR REPLACE PROCEDURE EliminarProveedor(p_id_proveedor IN proveedor.id_proveedor%TYPE)
IS
BEGIN
    DELETE FROM proveedor WHERE id_proveedor = p_id_proveedor;
END EliminarProveedor;

-----------------------------------------------------------------------------

--14 SP

--  Agregar un nuevo suministro

CREATE OR REPLACE PROCEDURE AgregarSuministro(
    p_id_suministro IN suministro.id_suministro%TYPE,
    p_nombre IN suministro.nombre%TYPE,
    p_descripcion IN suministro.descripcion%TYPE,
    p_cantidad IN suministro.cantidad%TYPE,
    p_precio_unitario IN suministro.precio_unitario%TYPE,
    p_id_proveedor IN suministro.id_proveedor%TYPE,
    p_id_hotel IN suministro.id_hotel%TYPE
)
IS
BEGIN
    INSERT INTO suministro (id_suministro, nombre, descripcion, cantidad, precio_unitario, id_proveedor, id_hotel)
    VALUES (p_id_suministro, p_nombre, p_descripcion, p_cantidad, p_precio_unitario, p_id_proveedor, p_id_hotel);
END AgregarSuministro;

-----------------------------------------------------------------------------

--15 SP

--  Actualizar un suministro

CREATE OR REPLACE PROCEDURE ActualizarSuministro(
    p_id_suministro IN suministro.id_suministro%TYPE,
    p_nombre IN suministro.nombre%TYPE,
    p_descripcion IN suministro.descripcion%TYPE,
    p_cantidad IN suministro.cantidad%TYPE,
    p_precio_unitario IN suministro.precio_unitario%TYPE,
    p_id_proveedor IN suministro.id_proveedor%TYPE,
    p_id_hotel IN suministro.id_hotel%TYPE
)
IS
BEGIN
    UPDATE suministro
    SET nombre = p_nombre, descripcion = p_descripcion, cantidad = p_cantidad, precio_unitario = p_precio_unitario, id_proveedor = p_id_proveedor, id_hotel = p_id_hotel
    WHERE id_suministro = p_id_suministro;
END ActualizarSuministro;

-----------------------------------------------------------------------------

--16 SP

--  Agregar una nueva habitacion 

CREATE OR REPLACE PROCEDURE AgregarHabitacion(
    p_id_habitacion IN habitacion.id_habitacion%TYPE,
    p_tipo_habitacion IN habitacion.tipo_habitacion%TYPE,
    p_precio IN habitacion.precio%TYPE,
    p_capacidad IN habitacion.capacidad%TYPE,
    p_disponibilidad IN habitacion.disponibilidad%TYPE
)
IS
BEGIN
    INSERT INTO habitacion (id_habitacion, tipo_habitacion, precio, capacidad, disponibilidad)
    VALUES (p_id_habitacion, p_tipo_habitacion, p_precio, p_capacidad, p_disponibilidad);
END AgregarHabitacion;

-----------------------------------------------------------------------------

--17 SP

--  Modificar habitacion 

CREATE OR REPLACE PROCEDURE ActualizarHabitacion(
    p_id_habitacion IN habitacion.id_habitacion%TYPE,
    p_tipo_habitacion IN habitacion.tipo_habitacion%TYPE,
    p_precio IN habitacion.precio%TYPE,
    p_capacidad IN habitacion.capacidad%TYPE,
    p_disponibilidad IN habitacion.disponibilidad%TYPE
)
IS
BEGIN
    UPDATE habitacion
    SET tipo_habitacion = p_tipo_habitacion,
        precio = p_precio,
        capacidad = p_capacidad,
        disponibilidad = p_disponibilidad
    WHERE id_habitacion = p_id_habitacion;
END ActualizarHabitacion;
-----------------------------------------------------------------------------

--18 SP

--  Eliminar habitacion 

CREATE OR REPLACE PROCEDURE EliminarHabitacion(
    p_id_habitacion IN habitacion.id_habitacion%TYPE
)
IS
BEGIN
    DELETE FROM habitacion WHERE id_habitacion = p_id_habitacion;
END EliminarHabitacion;

-----------------------------------------------------------------------------
--19 SP

--  Cancelar una reserva 

CREATE OR REPLACE PROCEDURE CancelarReserva(
    p_id_reserva IN reserva.id_reserva%TYPE
)
IS
BEGIN
    UPDATE reserva
    SET estado_reserva = 'Cancelada'
    WHERE id_reserva = p_id_reserva;
END CancelarReserva;
-----------------------------------------------------------------------------
--20 SP

--  Reservas activas

CREATE OR REPLACE PROCEDURE ConsultarReservasActivasPorCliente(
    p_id_cliente IN reserva.id_cliente%TYPE,
    cur_reservas OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN cur_reservas FOR
        SELECT id_reserva, fecha_inicio, fecha_fin, estado_reserva, id_habitacion
        FROM reserva
        WHERE id_cliente = p_id_cliente
          AND estado_reserva = 'Activa';
END ConsultarReservasActivasPorCliente;


-----------------------------------------------------------------------------
--21 SP

--  Consultar pagos por reserva 

CREATE OR REPLACE PROCEDURE ConsultarPagosPorReserva(
    p_id_reserva IN pago.id_reserva%TYPE
)
IS
BEGIN
    FOR r IN (
        SELECT id_pago, monto, fecha_pago, metodo_pago, id_factura
        FROM pago
        WHERE id_reserva = p_id_reserva
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID Pago: ' || r.id_pago || ', Monto: ' || r.monto || ', Fecha Pago: ' || r.fecha_pago || ', Metodo Pago: ' || r.metodo_pago || ', ID Factura: ' || r.id_factura);
    END LOOP;
END ConsultarPagosPorReserva;

-----------------------------------------------------------------------------
--22 SP

--  Consultar todos los pagos


CREATE OR REPLACE PROCEDURE ConsultarTodosLosPagos
IS
BEGIN
    FOR r IN (
        SELECT id_pago, id_reserva, monto, fecha_pago, metodo_pago, id_factura
        FROM pago
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID Pago: ' || r.id_pago || ', ID Reserva: ' || r.id_reserva || ', Monto: ' || r.monto || ', Fecha Pago: ' || r.fecha_pago || ', Metodo Pago: ' || r.metodo_pago || ', ID Factura: ' || r.id_factura);
    END LOOP;
END ConsultarTodosLosPagos;

-----------------------------------------------------------------------------
--23 SP


--Agregar una reserva

CREATE OR REPLACE PROCEDURE AgregarReserva(
    p_id_reserva IN reserva.id_reserva%TYPE,
    p_id_cliente IN reserva.id_cliente%TYPE,
    p_id_hotel IN reserva.id_hotel%TYPE,
    p_fecha_inicio IN reserva.fecha_inicio%TYPE,
    p_fecha_fin IN reserva.fecha_fin%TYPE,
    p_estado_reserva IN reserva.estado_reserva%TYPE,
    p_id_habitacion IN reserva.id_habitacion%TYPE
)
IS
BEGIN
    INSERT INTO reserva (id_reserva, id_cliente, id_hotel, fecha_inicio, fecha_fin, estado_reserva, id_habitacion)
    VALUES (p_id_reserva, p_id_cliente, p_id_hotel, p_fecha_inicio, p_fecha_fin, p_estado_reserva, p_id_habitacion);
END AgregarReserva;




-----------------------------------------------------------------------------
--1. Vista clientes registrados
CREATE VIEW vista_clientesRegistrados 
AS SELECT id_cliente, nombre, apellido1, apellido2
FROM CLIENTE;

--Para visualizar la vista
SELECT * FROM vista_clientesRegistrados;
-----------------------------------------------------------------------------

--2da Vista

--2. Vista ubicaciones del cliente 
CREATE VIEW vista_direccionClientes 
AS SELECT id_cliente, direccion
FROM CLIENTE;

--Para visualizar la 2da vista
SELECT * FROM vista_direccionClientes;
----------------------------------------------------------------------------

--3era Vista

--3. Vista empleado registrados
CREATE VIEW vista_empleadosRegistrados
AS SELECT id_empleado, nombre, apellido
FROM EMPLEADO;

--Para ver la visualizar la 3era vista
SELECT * FROM vista_empleadosRegistrados;
----------------------------------------------------------------------------

-- Vista 4

--4. Habitaciones Disponibles 
CREATE VIEW vista_habitaciones_disponibles AS
SELECT id_habitacion, tipo_habitacion, precio, capacidad
FROM habitacion
WHERE disponibilidad = 1;


--Para ver la visualizar la vista 4
SELECT * FROM habitaciones_disponibles;
----------------------------------------------------------------------------

-- Vista 5

--5. Proveedores por hotel
CREATE VIEW vista_proveedores_por_hotel AS
SELECT h.nombre AS hotel, p.nombre AS proveedor, p.telefono, p.email
FROM proveedor p
JOIN hotel h ON p.id_hotel = h.id_hotel;



--Para ver la visualizar la vista 5
SELECT * FROM vista_proveedores_por_hotel;
----------------------------------------------------------------------------

-- Vista 6

--6. Suministros por hotel
CREATE VIEW vista_suministros_por_hotel AS
SELECT h.nombre AS hotel, s.nombre AS proveedor, s.cantidad
FROM suministro s
JOIN hotel h ON s.id_hotel = h.id_hotel;

--Para ver la visualizar la vista 5
SELECT * FROM vista_suministros_por_hotel;
----------------------------------------------------------------------------

-- Vista 7

--7. Historial de los pagos por cliente

CREATE VIEW vista_historial_pagos_cliente AS
SELECT p.id_pago, c.nombre, c.apellido1, r.id_reserva, p.monto, p.fecha_pago
FROM cliente c
JOIN reserva r ON c.id_cliente = r.id_cliente
JOIN pago p ON r.id_reserva = p.id_reserva;


SELECT * FROM vista_historial_pagos_cliente;

----------------------------------------------------------------------------

-- Vista 8

--8. Reservas por fechas inicio/fin - CLIENTE-HOTEL-RESERVA

CREATE VIEW vista_reservas_por_fecha AS
SELECT r.id_reserva, c.nombre AS cliente, h.nombre AS hotel, r.fecha_inicio, r.fecha_fin, r.estado_reserva
FROM reserva r
JOIN cliente c ON r.id_cliente = c.id_cliente
JOIN hotel h ON r.id_hotel = h.id_hotel;

SELECT * FROM vista_reservas_por_fecha;
    

----------------------------------------------------------------------------

-- Vista 9

--9. Reservas al restaurante

CREATE VIEW vista_reservas_restaurante AS
SELECT r.nombre AS nombre_restaurante, h.nombre AS nombre_hotel
FROM restaurante r
JOIN hotel h ON h.id_hotel = r.id_hotel;

SELECT * FROM vista_reservas_restaurante;
    
----------------------------------------------------------------------------

-- Vista 10

--10. Facturas por fecha

CREATE VIEW vista_facturas_por_fecha AS
SELECT id_factura, id_reserva, fecha_emision, monto_total
FROM factura
WHERE fecha_emision = TO_DATE('&fecha', 'DD/MM/YYYY');

SELECT * FROM vista_facturas_por_fecha;



------------------ FUNCIONES ----------------------------

---- 1- Numero de reservas por clientes 

CREATE FUNCTION reservas_por_cliente(id_cliente INT)
RETURNS INT
BEGIN
    RETURN (
        SELECT COUNT(*)
        FROM reserva
        WHERE id_cliente = id_cliente
    );
END;

---- 2- Numero de reservas por clientes 