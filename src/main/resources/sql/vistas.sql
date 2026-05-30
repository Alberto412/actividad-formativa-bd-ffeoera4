CREATE OR REPLACE VIEW vista_partes_completas AS
SELECT
    pt.id,
    pt.titulo,
    pt.descripcion,
    pt.fecha,
    pt.horas_trabajadas,
    pt.estado,
    c.nombre AS cliente,
    c.empresa AS empresa_cliente,
    t.nombre AS tecnico,
    t.especialidad AS especialidad_tecnico
FROM parte_trabajo pt
JOIN cliente c ON pt.cliente_id = c.id
JOIN tecnico t ON pt.tecnico_id = t.id;

CREATE OR REPLACE VIEW vista_materiales_usados AS
SELECT
    pm.id,
    pt.titulo AS parte_trabajo,
    m.nombre AS material,
    m.descripcion AS descripcion_material,
    pm.cantidad,
    m.precio_unitario,
    (pm.cantidad * m.precio_unitario) AS coste_total_material
FROM parte_material pm
JOIN parte_trabajo pt ON pm.parte_trabajo_id = pt.id
JOIN material m ON pm.material_id = m.id;

CREATE OR REPLACE VIEW vista_coste_partes AS
SELECT
    pt.id,
    pt.titulo,
    c.nombre AS cliente,
    t.nombre AS tecnico,
    pt.estado,
    COALESCE(SUM(pm.cantidad * m.precio_unitario), 0) AS coste_materiales
FROM parte_trabajo pt
JOIN cliente c ON pt.cliente_id = c.id
JOIN tecnico t ON pt.tecnico_id = t.id
LEFT JOIN parte_material pm ON pm.parte_trabajo_id = pt.id
LEFT JOIN material m ON pm.material_id = m.id
GROUP BY pt.id, pt.titulo, c.nombre, t.nombre, pt.estado;
