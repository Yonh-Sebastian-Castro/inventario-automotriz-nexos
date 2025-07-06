/***********************************************************************************/
/* Nombre del Script: 05_validation_cases.sql                                     */
/* Fecha de creación: 05/07/2025                                                  */
/* Objetivo: Validar restricciones y reglas de negocio del sistema de inventario */
/* Versión PostgreSQL: 17.5                                                       */
/***********************************************************************************/

-- Caso 1: Insertar mercancía con nombre duplicado
DO $$
BEGIN
    INSERT INTO mercancia (nombre, cantidad, fecha_ingreso, usuario_registra_id)
    VALUES ('Filtro de aceite', 10, CURRENT_DATE, 1);
    RAISE NOTICE 'Caso 1: Inserción duplicada realizada (ERROR ESPERADO NO OCURRIÓ)';
EXCEPTION
    WHEN unique_violation THEN
        RAISE NOTICE 'Caso 1: ERROR ESPERADO -> No se puede insertar mercancía con nombre duplicado.';
    WHEN OTHERS THEN
        RAISE NOTICE 'Caso 1: ERROR inesperado: %', SQLERRM;
END
$$;

-- Caso 2: Insertar mercancía con cantidad negativa
DO $$
BEGIN
    INSERT INTO mercancia (nombre, cantidad, fecha_ingreso, usuario_registra_id)
    VALUES ('Producto inválido', -5, CURRENT_DATE, 1);
    RAISE NOTICE 'Caso 2: Inserción con cantidad negativa realizada (ERROR ESPERADO NO OCURRIÓ)';
EXCEPTION
    WHEN check_violation THEN
        RAISE NOTICE 'Caso 2: ERROR ESPERADO -> No se permite cantidad negativa.';
    WHEN OTHERS THEN
        RAISE NOTICE 'Caso 2: ERROR inesperado: %', SQLERRM;
END
$$;

-- Caso 3: Insertar mercancía con fecha futura
DO $$
BEGIN
    INSERT INTO mercancia (nombre, cantidad, fecha_ingreso, usuario_registra_id)
    VALUES ('Producto futuro', 10, CURRENT_DATE + 5, 1);
    RAISE NOTICE 'Caso 3: Inserción con fecha futura realizada (ERROR ESPERADO NO OCURRIÓ)';
EXCEPTION
    WHEN check_violation THEN
        RAISE NOTICE 'Caso 3: ERROR ESPERADO -> No se permite fecha de ingreso futura.';
    WHEN OTHERS THEN
        RAISE NOTICE 'Caso 3: ERROR inesperado: %', SQLERRM;
END
$$;

-- Caso 4: Inserción válida para comparación
DO $$
BEGIN
    INSERT INTO mercancia (nombre, cantidad, fecha_ingreso, usuario_registra_id)
    VALUES ('Producto válido', 25, CURRENT_DATE, 1);
    RAISE NOTICE 'Caso 4: Inserción válida realizada correctamente.';
EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'Caso 4: ERROR inesperado: %', SQLERRM;
END
$$;

-- Caso 5: Eliminar mercancía creada por otro usuario (esperado: no permitido)
-- Suponiendo que el usuario con ID 2 intenta eliminar mercancía registrada por ID 1
DO $$
DECLARE
    registros_eliminados INTEGER;
BEGIN
    DELETE FROM mercancia
    WHERE nombre = 'Producto válido' AND usuario_registra_id = 2;
    
    GET DIAGNOSTICS registros_eliminados = ROW_COUNT;
    
    IF registros_eliminados = 0 THEN
        RAISE NOTICE 'Caso 5: ERROR ESPERADO -> No se puede eliminar mercancía registrada por otro usuario.';
    ELSE
        RAISE NOTICE 'Caso 5: Eliminación realizada (ERROR ESPERADO NO OCURRIÓ).';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'Caso 5: ERROR inesperado: %', SQLERRM;
END
$$;

/***********************************************************************************/
/* Resultado esperado:
   Caso 1: Error por nombre duplicado.
   Caso 2: Error por cantidad negativa.
   Caso 3: Error por fecha futura.
   Caso 4: Inserción válida exitosa.
   Caso 5: No eliminación si no es el usuario creador.
*/
/***********************************************************************************/
