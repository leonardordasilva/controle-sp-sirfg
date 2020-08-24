$('document').ready(function () {
    montarDateRangePicker();

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

    function montarDateRangePicker() {
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
    }

    $('#mainTable').on('draw.dt', function () {
        montarDateRangePicker();
    });

    $('#editModal').on('shown.bs.modal', function(e) {
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
    });

    $('#modalObj').on('hidden.bs.modal', function () {
        $(this).find('form').trigger('reset');
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

    $('#addAmbienteButton').on('click', function (event) {
        event.preventDefault();
        $('#formAmbiente').trigger("reset");

        $('#formAmbiente').attr("action", "/ambiente/adicionar");
        $('#tituloModal').text("Adicionar Ambiente");
        $('#tituloBotaoModal').text("ADICIONAR");

        $('#ambienteModal').modal({
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

        $('#formAmbiente').attr("action", "/ambiente/atualizar");
        $('#tituloModal').text("Alterar Ambiente");
        $('#tituloBotaoModal').text("ALTERAR");

        $('#ambienteModal').modal({
            keyboard: true
        });
    });
});