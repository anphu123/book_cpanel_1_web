
$(document).ready(function () {
    let table = $('#employeeTable').DataTable({
        order: [[0, 'asc']],
        lengthMenu:[5],
        // "ordering": false,
        "info":     false,
        dom:'<"top"1>rt<"bottom"ipr><"clear">'
    });
    table.columns(1).every( function() {
        let that = this;
        $('#myInput').on('keyup change', function() {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();}
        });
    });
});
$(document).ready(function () {
  $('#productTable').DataTable({
    "scrollY": "50vh",
    "scrollCollapse": true,
  });
  $('.dataTables_length').addClass('bs-select');
});


