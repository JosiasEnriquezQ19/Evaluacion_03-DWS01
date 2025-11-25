# Manual de Usuario - Blog Interno EVA-03

## 📚 Guía de Usuario del Sistema

**Sistema:** Blog Interno - Gestión de Autores y Publicaciones
**Versión:** 1.0
**Destinado a:** Administradores del sistema de blog

---

## 🚀 Introducción

El sistema **Blog Interno** es una aplicación web diseñada para gestionar autores y sus publicaciones de manera eficiente. Permite controlar todo el flujo de trabajo editorial desde la creación de contenido hasta su publicación.

### Características Principales

- 👥 Gestión completa de autores
- 📝 Control de publicaciones con estados
- ✅ Validaciones automáticas
- 📱 Interfaz responsive

---

## 👥 Gestión de Autores

### Listar Autores

**Ubicación:** Menú lateral > "Autores" o URL `/autor`

**Funcionalidades:**

- 📋 Vista de todos los autores registrados
- 🔍 Búsqueda por nombre
- 📊 Estadísticas de publicaciones por autor
- 🏷️ Estados visuales (Activo/Inactivo)

**Columnas de Información:**

- **#:** Número consecutivo
- **Autor:** Nombre del autor
- **Correo:** Email de contacto
- **Estado:** Activo o Inactivo
- **Publicaciones:** Cantidad de publicaciones
- **Acciones:** Botones de editar y ver detalles

### Registrar Nuevo Autor

**Acceso:** Botón "➕ Nuevo Autor" en la lista de autores

**Campos Requeridos:**

- **Nombre:** 2-100 caracteres *(Obligatorio)*
- **Email:** Formato válido de correo *(Obligatorio, único)*
- **Estado:** Activo (por defecto)

**Validaciones Automáticas:**

- ✅ Nombre: Mínimo 2 caracteres, máximo 100
- ✅ Email: Formato válido (ejemplo@dominio.com)
- ✅ Email único: No puede repetirse en el sistema

**Pasos para Registrar:**

1. Clic en "➕ Nuevo Autor"
2. Llenar formulario con datos válidos
3. El sistema valida automáticamente
4. Clic en "Guardar Autor"
5. Confirmación de registro exitoso

### Editar Autor

**Acceso:** Clic en "✏️" junto al autor en la lista

**Modificaciones Permitidas:**

- Cambiar nombre
- Actualizar email (debe seguir siendo único)
- Cambiar estado (Activo ↔ Inactivo)

**Funciones Especiales:**

- **Ver Publicaciones:** Lista todas las publicaciones del autor
- **Nueva Publicación:** Crear contenido asignado al autor
- **Cambiar Estado:** Toggle rápido Activo/Inactivo

---

## 📝 Gestión de Publicaciones

### Listar Publicaciones

**Ubicación:** Menú lateral > "Publicaciones" o URL `/publicacion`

**Dashboard de Publicaciones:**

- 📊 **Total Publicaciones:** Contador general
- 🟢 **Publicadas:** Contenido público
- 🟡 **Borradores:** Trabajo en progreso
- 🔒 **Archivadas:** Contenido archivado

**Filtros Disponibles:**

- 🔍 **Por Estado:** Todos, Borrador, Publicado, Archivado
- 📅 **Por Fecha:** Ordenamiento cronológico
- 👤 **Por Autor:** Filtrar por autor específico

**Vista de Tabla:**

- **#:** Número consecutivo
- **Título:** Nombre de la publicación con preview
- **Autor:** Autor asignado (ID)
- **Estado:** Badge visual del estado actual
- **Fecha Pub.:** Fecha de publicación
- **Modificado:** Última modificación
- **Acciones:** Ver, editar, cambiar estado

### Crear Nueva Publicación

**Acceso:** Botón "➕ Nueva Publicación"

**Campos del Formulario:**

1. **Título** *(Obligatorio)*

   - Longitud: 10-100 caracteres
   - Contador dinámico de caracteres
   - Validación en tiempo real
2. **Autor** *(Obligatorio)*

   - Selección desde lista desplegable
   - Solo autores activos disponibles
3. **Estado** *(Obligatorio)*

   - **Borrador:** Para trabajo en progreso
   - **Publicado:** Para contenido público
   - **Archivado:** Para contenido archivado
4. **Contenido** *(Obligatorio)*

   - Longitud: 100-2000 caracteres
   - Editor de texto enriquecido
   - Contador de caracteres
5. **Fechas** *(Automáticas)*

   - Fecha de publicación: Asignada automáticamente
   - Fecha de modificación: Actualizada automáticamente

**Acciones de Guardado:**

- 💾 **Guardar Borrador:** Guarda como borrador
- ✅ **Publicar:** Cambia estado a publicado directamente

### Editar Publicación

**Acceso:** Clic en "✏️" junto a la publicación

**Funcionalidades de Edición:**

**Panel Principal:**

- Edición completa de título y contenido
- Cambio de autor asignado
- Control de estado con validaciones
- Actualización automática de fecha de modificación

**Panel Lateral:**

- **Información Actual:** Resumen de datos
- **Validaciones en Tiempo Real:** Estado de cada campo
- **Consejos de Edición:** Mejores prácticas
- **Acciones Rápidas:** Guardar, cancelar, eliminar

**Validaciones Especiales:**

- ✅ Título entre 5-150 caracteres (RNF7.V1)
- ✅ Autor obligatorio (RNF7.V3)
- ✅ Contenido mínimo requerido
- ✅ Estados válidos (RNF7.V4)

### Ver Detalle de Publicación

**Acceso:** Clic en el título de la publicación

**Vista de Lectura:**

- 📖 Formato estilo artículo/blog
- 👤 Información del autor
- 📅 Fechas de creación y modificación
- ⚙️ Botones de acción (Editar, Eliminar)
- 🔄 Control de estados

### Cambiar Estado de Publicación

**Estados Disponibles:**

- 🟡 **BORRADOR:** Trabajo en progreso, no visible públicamente
- 🟢 **PUBLICADO:** Contenido público disponible
- 🔒 **ARCHIVADO:** Contenido retirado pero preservado

**Transiciones Permitidas:**

- Borrador → Publicado
- Borrador → Archivado
- Publicado → Archivado
- Archivado → Borrador (para reactivar)

**Cómo Cambiar Estado:**

1. En la lista de publicaciones, clic en "⚙️"
2. Seleccionar nuevo estado del menú
3. Confirmación automática del cambio
4. Actualización visual del badge de estado

---

## 🎨 Interfaz de Usuario

### Navegación Principal

**Barra Lateral (Sidebar):**

- 🏠 **Inicio:** Dashboard principal
- 👥 **Autores:** Gestión de autores
- 📝 **Publicaciones:** Gestión de contenido
- 📊 **Reportes:** Muestra página de error demo

**Barra Superior:**

- 📱 **Toggle Menú:** Para dispositivos móviles
- 🔍 **Búsqueda Global:** (Próxima implementación)
- ⚙️ **Configuraciones:** (Próxima implementación)

### Elementos Visuales

**Badges de Estado:**

- 🟢 Verde: Activo/Publicado
- 🟡 Amarillo: Borrador
- 🔒 Gris: Inactivo/Archivado

**Iconografía:**

- ✏️ Editar
- 👁️ Ver detalles
- 🗑️ Eliminar
- ➕ Agregar nuevo
- 🔄 Cambiar estado

### Responsive Design

**Adaptaciones por Pantalla:**

- 📱 **Móvil:** Menú colapsable, tablas con scroll
- 💻 **Tablet:** Vista intermedia optimizada
- 🖥️ **Desktop:** Vista completa con sidebar fijo

---

## ✅ Validaciones y Reglas

### Validaciones de Autor

**Nombre:**

- ✅ Mínimo: 2 caracteres
- ✅ Máximo: 100 caracteres
- ✅ No puede estar vacío

**Email:**

- ✅ Formato válido: ejemplo@dominio.com
- ✅ Único en el sistema
- ✅ No puede estar vacío

### Validaciones de Publicación

**Título (RNF7.V1):**

- ✅ Mínimo: 5 caracteres
- ✅ Máximo: 150 caracteres
- ✅ Contador visual en tiempo real

**Contenido:**

- ✅ Mínimo: 100 caracteres
- ✅ Máximo: 2000 caracteres
- ✅ No puede estar vacío

**Autor (RNF7.V3):**

- ✅ Debe estar asignado
- ✅ Solo autores activos
- ✅ No puede estar vacío

### Retroalimentación Visual

**Estados de Validación:**

- 🟢 **Verde:** Campo válido
- 🔴 **Rojo:** Error en el campo
- ⚪ **Neutral:** Campo sin validar aún

**Mensajes de Error:**

- Aparecen debajo de cada campo
- Explicación clara del problema
- Desaparecen al corregir el error

---

## 🚨 Manejo de Errores

### Páginas de Error Personalizadas

**Error 404 - Página No Encontrada:**

- Mensaje amigable al usuario
- Navegación alternativa
- Botón de regreso al inicio

**Error 500 - Error del Servidor:**

- Información del problema
- Opciones de reporte
- Contacto de soporte técnico

**Error General:**

- Captura cualquier error no específico
- Información técnica básica
- Opciones de navegación

### Mensajes del Sistema

**Éxito:**

- ✅ "Autor guardado exitosamente"
- ✅ "Publicación actualizada"
- ✅ "Estado cambiado correctamente"

**Errores de Validación:**

- ❌ "El título debe tener entre 5 y 150 caracteres"
- ❌ "El email ya existe en el sistema"
- ❌ "Debe seleccionar un autor"

---

## 🔧 Solución de Problemas Comunes

### Problemas de Validación

**"No puedo guardar el autor"**

- Verificar que el email sea único
- Comprobar formato del email
- Asegurar que el nombre tenga mínimo 2 caracteres

**"El título no se acepta"**

- Contar caracteres (debe ser 5-150)
- Evitar caracteres especiales problemáticos
- No dejar el campo vacío

**"No aparece el autor en la lista"**

- Verificar que el autor esté activo
- Refrescar la página
- Comprobar si se guardó correctamente

### Problemas de Navegación

**"No puedo ver el menú en móvil"**

- Usar el botón ☰ en la esquina superior izquierda
- El menú se colapsa automáticamente en pantallas pequeñas

**"La página no carga"**

- Verificar conexión a internet
- Comprobar que el servidor esté ejecutándose
- Limpiar caché del navegador

### Problemas de Datos

**"No veo mis cambios"**

- Los cambios se guardan automáticamente
- Refrescar la página para ver actualizaciones
- Verificar que no hayan errores de validación

---

## 📱 Acceso desde Dispositivos Móviles

### Características Móviles

**Navegación:**

- Menú hamburguesa colapsable
- Navegación por gestos
- Botones de tamaño táctil apropiado

**Formularios:**

- Teclados adaptativos (email, texto)
- Campos optimizados para touch
- Validación visual clara

**Tablas:**

- Scroll horizontal automático
- Información prioritaria visible
- Acciones adaptadas a touch

### Mejores Prácticas Móviles

1. **Usar modo paisaje** para tablas extensas
2. **Aprovechar validación automática** para reducir errores
3. **Usar botones grandes** para mejor precisión
4. **Revisar contenido** antes de guardar en pantallas pequeñas

---

## 🎯 Casos de Uso Típicos

### Flujo de Trabajo: Nuevo Autor y Publicación

1. **Registrar Autor:**

   - Ir a Autores > Nuevo Autor
   - Llenar nombre y email
   - Guardar
2. **Crear Primera Publicación:**

   - Ir a Publicaciones > Nueva Publicación
   - Seleccionar autor recién creado
   - Escribir título y contenido
   - Guardar como borrador
3. **Revisar y Publicar:**

   - Editar publicación para refinar
   - Cambiar estado a "Publicado"
   - Verificar en lista de publicaciones

### Flujo de Mantenimiento

1. **Revisar Dashboard:** Ver estadísticas generales
2. **Gestión de Estados:** Cambiar publicaciones según necesidad
3. **Mantenimiento de Autores:** Activar/desactivar según sea necesario
4. **Archivado:** Mover contenido obsoleto a archivado

---

## 📊 Interpretación de Estadísticas

### Dashboard Principal

**Métricas Clave:**

- **Total Autores:** Indica el tamaño de tu equipo editorial
- **Total Publicaciones:** Volumen total de contenido
- **Publicaciones Publicadas:** Contenido público disponible
- **Publicaciones Archivadas:** Historial preservado

**Indicadores de Salud:**

- Alto porcentaje de autores activos = Equipo comprometido
- Balance entre borradores y publicados = Flujo saludable
- Pocas publicaciones archivadas = Contenido relevante

### Interpretación de Estados

**BORRADOR:**

- Normal: Trabajo en progreso
- Exceso: Posible embotellamiento editorial
- Pocos: Publicación muy rápida o poco contenido nuevo

**PUBLICADO:**

- Alto: Buena productividad
- Bajo: Revisar proceso editorial
- Creciente: Tendencia positiva

**ARCHIVADO:**

- Moderado: Gestión normal de ciclo de vida
- Exceso: Posible problema de relevancia
- Cero: Todo el contenido sigue vigente

---

## 📞 Soporte y Ayuda

### Contacto Técnico

Para problemas técnicos o consultas sobre el sistema:

- **Desarrollador:** [Nombre del Estudiante]
- **Curso:** Desarrollo de Servicios Web 1
- **Institución:** IDAT - 5to Ciclo

### Reportar Errores

Al encontrar un problema:

1. Anotar pasos para reproducir el error
2. Capturar pantalla si es necesario
3. Reportar URL donde ocurrió
4. Describir comportamiento esperado vs actual

---

**Manual de Usuario - Blog Interno EVA-03**
*Versión 1.0 - Noviembre 2024*
*IDAT - Desarrollo de Servicios Web 1*
