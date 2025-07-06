/***********************************************************************************/
/* Nombre del Script: 01a_drop_database_if_exists.sql                              */
/* Fecha de creacion: 04/07/2025                                                   */
/* Objetivo:          Elimina la base de datos 'inventario_automotriz' si existe   */
/* Version PostgreSQL: 17.5                                                        */
/***********************************************************************************/

DO $$
BEGIN
    IF EXISTS (SELECT FROM pg_database WHERE datname = 'inventario_automotriz') THEN
        RAISE NOTICE 'La base de datos inventario_automotriz ya existe. Se eliminará.';
        
        -- Cierra conexiones activas
        PERFORM pg_terminate_backend(pid)
        FROM pg_stat_activity
        WHERE datname = 'inventario_automotriz' AND pid <> pg_backend_pid();
        
        DROP DATABASE inventario_automotriz;
        RAISE NOTICE 'Base de datos eliminada exitosamente.';
    ELSE
        RAISE NOTICE 'La base de datos inventario_automotriz no existe. No se elimina nada.';
    END IF;
END $$;

