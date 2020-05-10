$('document').ready(function () {
    $('input[name^="data"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'), 10),
        timePicker: true,
        timePicker24Hour: true,
        startDate: moment().startOf('hour minute'),
        autoUpdateInput: false,
        locale: {
            "format": "DD/MM/YYYY HH:mm",
            "applyLabel": "Aplicar",
            "cancelLabel": "Cancelar",
            "daysOfWeek": [
                "Dom",
                "Seg",
                "Ter",
                "Qua",
                "Qui",
                "Sex",
                "Sab"
            ],
            "monthNames": [
                "Janeiro",
                "Fevereiro",
                "Março",
                "Abril",
                "Maio",
                "Junho",
                "Julho",
                "Agosto",
                "Setembro",
                "Outubro",
                "Novembro",
                "Dezembro"
            ],
            "firstDay": 1
        }
    });

    $('input[name^="data"]').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY HH:mm'));
    });

    $('input[name^="data"]').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
    });

    $('#mainTable').dataTable({
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "_MENU_ resultados por página",
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch": "Pesquisar",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            },
            "select": {
                "rows": {
                    "_": "Selecionado %d linhas",
                    "0": "Nenhuma linha selecionada",
                    "1": "Selecionado 1 linha"
                }
            }
        }
    });

    $('.table .btn-warning').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (sp, status) {
            $('#idEdit').val(sp.id);
            $('#nomeEdit').val(sp.nome);
            $('#dataDesEdit').val(sp.dataDesFormatado);
            $('#dataTqsEdit').val(sp.dataTqsFormatado);
            $('#dataHmpEdit').val(sp.dataHmpFormatado);
            $('#dataPrdEdit').val(sp.dataPrdFormatado);
        });

        $('#editModal').modal();
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });

    $('#deleteAllButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteAllModal #delAllRef').attr('href', href);
        $('#deleteAllModal').modal();
    });
});