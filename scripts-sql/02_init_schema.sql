/***********************************************************************************/
/* Nombre del Script: 02_init_schema.sql                                           */
/* Fecha de creacion: 04/07/2025                                                   */
/* Objetivo:          Crear el esquema de tablas del sistema de inventario para el */
/*                    sector automotriz donde se controle la mercancia que ingresa */
/*                    y la que sale.                                               */
/* Version PostgreSQL: 17.5                                                        */
/***********************************************************************************/

DO $$
DECLARE
    tabla_existente BOOLEAN;
BEGIN
    -- Verificar y eliminar tabla mercancia
    SELECT EXISTS (
        SELECT 1 FROM pg_tables 
        WHERE tablename = 'mercancia' AND schemaname = 'public'
    ) INTO tabla_existente;

    IF tabla_existente THEN
        DROP TABLE mercancia;
        RAISE NOTICE 'Tabla mercancia eliminada.';
    END IF;

    -- Verificar y eliminar tabla usuarios
    SELECT EXISTS (
        SELECT 1 FROM pg_tables 
        WHERE tablename = 'usuarios' AND schemaname = 'public'
    ) INTO tabla_existente;

    IF tabla_existente THEN
        DROP TABLE usuarios;
        RAISE NOTICE 'Tabla usuarios eliminada.';
    END IF;

    -- Verificar y eliminar tabla cargos
    SELECT EXISTS (
        SELECT 1 FROM pg_tables 
        WHERE tablename = 'cargos' AND schemaname = 'public'
    ) INTO tabla_existente;

    IF tabla_existente THEN
        DROP TABLE cargos;
        RAISE NOTICE 'Tabla cargos eliminada.';
    END IF;

    -- Crear tabla de cargos
    CREATE TABLE cargos (
        id SERIAL PRIMARY KEY,
        nombre VARCHAR(50) UNIQUE NOT NULL
    );
    RAISE NOTICE 'Tabla cargos creada.';

    -- Insertar cargos iniciales
    INSERT INTO cargos (nombre) VALUES 
    ('Asesor de ventas'),
    ('Administrador'),
    ('Soporte');
    RAISE NOTICE 'Cargos iniciales insertados.';

    -- Crear tabla de usuarios
    CREATE TABLE usuarios (
        id SERIAL PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        edad INT CHECK (edad > 0),
        fecha_ingreso DATE NOT NULL,
        cargo_id INT NOT NULL REFERENCES cargos(id)
    );
    RAISE NOTICE 'Tabla usuarios creada.';

    -- Crear tabla de mercancia
    CREATE TABLE mercancia (
        id SERIAL PRIMARY KEY,
        nombre VARCHAR(100) UNIQUE NOT NULL,
        cantidad INT NOT NULL CHECK (cantidad >= 0),
        fecha_ingreso DATE NOT NULL CHECK (fecha_ingreso <= CURRENT_DATE),
        usuario_registra_id INT NOT NULL REFERENCES usuarios(id),
        usuario_modifica_id INT REFERENCES usuarios(id),
        fecha_modificacion DATE
    );
    RAISE NOTICE 'Tabla mercancia creada.';

EXCEPTION
    WHEN OTHERS THEN
        RAISE EXCEPTION 'Error durante la creacion del esquema: %', SQLERRM;
END $$;
