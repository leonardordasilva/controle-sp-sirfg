$('document').ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $('#mainTable').DataTable({
        columnDefs: [
            {
                targets: [0],
                visible: false,
                searchable: false
            }
        ],
        order: [1, 'asc'],
        pagingType: "full_numbers",
        language: {
            sEmptyTable: "Nenhum registro encontrado",
            sInfo: "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            sInfoEmpty: "Mostrando 0 até 0 de 0 registros",
            sInfoFiltered: "(Filtrados de _MAX_ registros)",
            sInfoPostFix: "",
            sInfoThousands: ".",
            sLengthMenu: "_MENU_ resultados por página",
            sLoadingRecords: "Carregando...",
            sProcessing: "Processando...",
            sZeroRecords: "Nenhum registro encontrado",
            sSearch: "Pesquisar",
            oPaginate: {
                sNext: "Próximo",
                sPrevious: "Anterior",
                sFirst: "Primeiro",
                sLast: "Último"
            },
            oAria: {
                sSortAscending: ": Ordenar colunas de forma ascendente",
                sSortDescending: ": Ordenar colunas de forma descendente"
            },
            select: {
                rows: {
                    _: "Selecionado %d linhas",
                    0: "Nenhuma linha selecionada",
                    1: "Selecionado 1 linha"
                }
            }
        }
    });

    $('input[name^="data"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 2000,
        maxYear: parseInt(moment().format('YYYY'), 10),
        timePicker: true,
        timePicker24Hour: true,
        startDate: moment().startOf('hour minute'),
        autoUpdateInput: false,
        locale: {
            "format": "DD/MM/YYYY HH:mm",
            "applyLabel": "Aplicar",
            "cancelLabel": "Limpar",
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

    $('input[name^="data"]').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY HH:mm'));
    });

    $('input[name^="data"]').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
    });

    $('#addSpButton').on('click', function (event) {
        event.preventDefault();
        $('#formObj').trigger("reset");

        $('#formObj').attr("action", "/adicionar");
        $('#tituloModal').text("Adicionar Objeto");
        $('#tituloBotaoModal').text("ADICIONAR");

        $('#modalObj').modal({
            keyboard: true
        });
    });

    $('#mainTable tbody').on('click', 'tr td a#editButton', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $('#formObj').attr("action", "/atualizar");
        $('#tituloModal').text("Atualizar Objeto");
        $('#tituloBotaoModal').text("ATUALIZAR");

        $.get(href, function (sp, status) {
            var tipoObjetoSelecionado = sp.tipoObjeto;

            $('#id').val(sp.id);
            $('#nome').val(sp.nome);
            $('#' + tipoObjetoSelecionado).prop('checked', true);
            $('#dataDes').val(sp.dataDesFormatada);
            $('#dataTqs').val(sp.dataTqsFormatada);
            $('#dataHmp').val(sp.dataHmpFormatada);
            $('#dataPrd').val(sp.dataPrdFormatada);
            $('#observacao').val(sp.observacao);
        });

        $('#modalObj').modal({
            keyboard: true
        });
    });

    $('#mainTable tbody').on('click', 'tr td a#deleteButton', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (sp, status) {
            $('#idDelete').val(sp.id);
            $('#nomeDelete').val(sp.nome);
            $('#nomeSpExcluir').text(sp.nome);
        });

        $('#deleteModal').modal({
            keyboard: true
        });
    });

    $('#mainTable tbody').on('click', 'tr td a#equalizarButton', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (sp, status) {
            $('#idEqualizar').val(sp.id);
            $('#nomeEqualizar').val(sp.nome);
            $('#tipoObjetoEqualizar').val(sp.tipoObjeto);
            $('#dataDesEqualizar').val(sp.dataDesFormatada);
            $('#dataTqsEqualizar').val(sp.dataTqsFormatada);
            $('#dataHmpEqualizar').val(sp.dataHmpFormatada);
            $('#dataPrdEqualizar').val(sp.dataPrdFormatada);
            $('#observacaoEqualizar').val(sp.observacao);

            $('#nomeSpEqualizar').text(sp.nome);
        });

        $('#equalizarModal').modal({
            keyboard: true
        });
    });

    $('#equalizarAllButton').on('click', function (event) {
        event.preventDefault();
        $('#equalizarAllModal').modal({
            keyboard: true
        });
    });

    $('#ambienteTable tbody').on('click', 'tr td a#ambienteEditButton', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (ambiente, status) {
            $('#idEdit').val(ambiente.id);
            $('#versaoDesEdit').val(ambiente.versaoDes);
            $('#dataDesEdit').val(ambiente.dataDesFormatada);
            $('#versaoTqsEdit').val(ambiente.versaoTqs);
            $('#dataTqsEdit').val(ambiente.dataTqsFormatada);
            $('#versaoHmpEdit').val(ambiente.versaoHmp);
            $('#dataHmpEdit').val(ambiente.dataHmpFormatada);
            $('#versaoPrdEdit').val(ambiente.versaoPrd);
            $('#dataPrdEdit').val(ambiente.dataPrdFormatada);
        });

        $('#ambienteEditModal').modal({
            keyboard: true
        });
    });
});