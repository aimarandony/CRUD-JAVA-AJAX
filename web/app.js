$(document).ready(() => {
    console.info('Jquery is ready !!');
    
    function Listar(){
        $.post('ServletProducto',{op:"listar"},(respuesta)=>{
            const datos = JSON.parse(respuesta);
            console.info(datos);
            let template = '';
            $('#tblProducto').dataTable().fnDestroy();
            datos.forEach(elem => {
                var customEstado = {texto:"INACTIVO",color:"danger"};
                if (elem.estado)  customEstado = {texto:"ACTIVO",color:"success"};
               template += `
                            <tr class="text-center">
                                <td>${elem.id}</td>
                                <td>${elem.nombre}</td>
                                <td>${elem.precio}</td>
                                <td>
                                    <span class="badge badge-${customEstado.color}">
                                        ${customEstado.texto}
                                    </span>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-warning btn-sm editar" id="${elem.id}" title="EDITAR">
                                            <i class="fas fa-pen"></i>
                                        </button>
                                        <button class="btn btn-danger btn-sm eliminar" id="${elem.id}" title="ELIMINAR">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            `;
            });
            $('#tbody-producto').html(template);
            $('#tblProducto').dataTable({
                "lengthMenu": [[4, 10, 25, 50, -1], [4, 10, 25, 50, "TODO"]],
                language: idioma_es
            });
        });
    }

    Listar();
    
    function Obtener(){
        var id = $('#id').val();
        var nombre = $('#nombre').val();
        var precio = $('#precio').val();
        
        var op = 'actualizar';
        if(id === ''){
            op = 'insertar';
        }
        
        const datos = {op,id,nombre,precio};
        return datos;
    }
    
    function Limpiar(){
        $('#id').val('');
        $('#nombre').val('');
        $('#precio').val('');
    }
    
    $('#guardar').click(() =>{
        const datos = Obtener();
        $.post('ServletProducto',datos,(respuesta) =>{
            console.log('¿GUARDÓ? : '+respuesta);
            const textSave = (datos.id === '') ? 'REGISTRADO' : 'ACTUALIZADO';
            const mensaje = (respuesta) ? '✔ '+textSave+' CORRECTAMENTE' : '❌ ERROR AL GUARDAR';            
            alert(mensaje);
            Listar();
            Limpiar();
        });
    });
    
    $(document).on('click','.editar', function(){
        const id = $(this).attr('id');
        console.info('EDITAR (id):',id);
        $.post('ServletProducto',{op:"listarID",id},(respuesta) =>{
            const producto = JSON.parse(respuesta);
            console.log(producto);
            $('#id').val(producto.id);
            $('#nombre').val(producto.nombre);
            $('#precio').val(producto.precio);
        });
    });
    
    $(document).on('click','.eliminar', function(){
        const id = $(this).attr('id');
        console.info('ELIMINAR (id):',id);
        if(confirm('¿Seguro que desea eliminar? 🤔'))
        $.post('ServletProducto',{op:"eliminar",id},(respuesta) =>{
            const mens = (respuesta) ? '✔ ELIMINADO CORRECTAMENTE' : '❌ ERROR AL ELIMINAR';
            alert(mens);
            Listar();
        });
    });
    
    //CDN DATATABLE - IDIOMA
    var idioma_es = {
        "sProcessing": "Procesando...",
        "sLengthMenu": "Mostrar _MENU_ registros",
        "sZeroRecords": "No se encontraron resultados",
        "sEmptyTable": "Ningún dato disponible en esta tabla",
        "sInfo": "_START_ al _END_ de un total de _TOTAL_ registros",
        "sInfoEmpty": "Ningún registro.",
        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
        "sInfoPostFix": "",
        "sSearch": "Buscar:",
        "sUrl": "",
        "sInfoThousands": ",",
        "sLoadingRecords": "Cargando...",
        "oPaginate": {
            "sFirst": "Primero",
            "sLast": "Último",
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        },
        "oAria": {
            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
        }
    };
});

