/***********************************************************************************/
/* Nombre del Script: 03_insert_sample_data.sql                                    */
/* Fecha de creacion: 04/07/2025                                                   */
/* Objetivo:          Inserta datos de ejemplo para pruebas funcionales            */
/* Version PostgreSQL: 17.5                                                        */
/***********************************************************************************/

-- Inserción de CARGOS (evita duplicados)
INSERT INTO cargos (nombre) VALUES 
    ('Asesor de ventas'),
    ('Administrador'),
    ('Soporte')
ON CONFLICT (nombre) DO NOTHING;

DO $$ BEGIN RAISE NOTICE 'Cargos insertados correctamente (sin duplicados).'; END $$;


-- Inserción de USUARIOS (solo si no existen usuarios con mismo nombre)
INSERT INTO usuarios (nombre, edad, cargo_id, fecha_ingreso) VALUES 
    ('Carlos Perez', 28, 1, '2023-06-10'),
    ('Laura Gomez', 35, 2, '2021-04-25'),
    ('Juan Rodriguez', 40, 3, '2022-11-15')
ON CONFLICT DO NOTHING;  -- solo aplicará si más adelante tienes una restricción única por nombre

DO $$ BEGIN RAISE NOTICE 'Usuarios insertados correctamente (sin duplicados).'; END $$;


-- Inserción de MERCANCÍA (nombres únicos)
INSERT INTO mercancia (nombre, cantidad, fecha_ingreso, usuario_registra_id) VALUES
    ('Filtro de aceite', 50, '2024-07-01', 1),
    ('Batería 12V', 20, '2024-07-02', 2),
    ('Aceite sintético', 100, '2024-06-25', 3),
    ('Filtro de aire', 75, '2024-06-30', 1)
ON CONFLICT (nombre) DO NOTHING;

DO $$ BEGIN RAISE NOTICE 'Mercancía insertada correctamente (sin duplicados).'; END $$;

/**********************************************************************************/
/* Consideraciones:                                                               */
/* 1. El script es reprocesable: no falla si se ejecuta varias veces.             */
/* 2. Usa ON CONFLICT DO NOTHING donde es necesario.                              */
/* 3. Evita errores por duplicados en cargos y mercancía.                         */
/* 4. Las claves foráneas dependen de IDs existentes.                             */
/**********************************************************************************/
