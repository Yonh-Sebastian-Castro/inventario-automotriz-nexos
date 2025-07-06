/***********************************************************************************/
/* Nombre del Script: 04_select_queries.sql                                        */
/* Fecha de creacion: 05/07/2025                                                   */
/* Objetivo:          Consultas de ejemplo para probar filtros funcionales sobre   */
/*                    la mercancia registrada en el sistema de inventario.         */
/* Version PostgreSQL: 17.5                                                        */
/***********************************************************************************/

-- Consulta 1: Buscar mercancia registrada por un usuario especifico (por nombre)
SELECT m.id, m.nombre, m.cantidad, m.fecha_ingreso, u.nombre AS usuario
FROM mercancia m
JOIN usuarios u ON m.usuario_registra_id = u.id
WHERE u.nombre ILIKE '%Carlos%';

DO $$ BEGIN RAISE NOTICE 'Consulta 1 ejecutada: Filtro por nombre de usuario.'; END $$;

-- Consulta 2: Buscar mercancia por fecha exacta de ingreso
SELECT * FROM mercancia
WHERE fecha_ingreso = '2024-06-30';

DO $$ BEGIN RAISE NOTICE 'Consulta 2 ejecutada: Filtro por fecha exacta.'; END $$;

-- Consulta 3: Buscar mercancia por nombre del producto (filtro parcial)
SELECT * FROM mercancia
WHERE nombre ILIKE '%filtro%';

DO $$ BEGIN RAISE NOTICE 'Consulta 3 ejecutada: Filtro por nombre de producto.'; END $$;

-- Consulta 4: Buscar mercancia usando multiples filtros
SELECT m.*, u.nombre AS usuario
FROM mercancia m
JOIN usuarios u ON u.id = m.usuario_registra_id
WHERE m.fecha_ingreso BETWEEN '2024-06-01' AND '2024-07-01'
  AND u.nombre ILIKE '%Laura%'
  AND m.nombre ILIKE '%bateria%';

DO $$ BEGIN RAISE NOTICE 'Consulta 4 ejecutada: Filtro combinado por fecha, usuario y nombre.'; END $$;
