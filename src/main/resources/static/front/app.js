$(document).ready(function () {

var source =
{
    dataType: "json",
    dataFields: [
        { name: 'id', type: 'int' },
        { name: 'description', type: 'string' },
        { name: 'location', type: 'string' },
        { name: 'subject', type: 'string' },
        { name: 'calendar', type: 'string' },
        { name: 'start', type: 'date' },
        { name: 'end', type: 'date' }
    ],
    url: '/calendar/all',
    id:'id'
};
var adapter = new $.jqx.dataAdapter(source);
    // ,{
    //     beforeLoadComplete: function (records) {
    //         for(var i=0;i<records.length;i++){
    //             records[i]['start'] = new Date(2019, 6, 17+i, 14, 0, 0);
    //             records[i]['end'] = new Date(2019, 6, 17+i, 16,2, 0, 0);
    //         }
    //         return records;
    //     }
    // }
$("#scheduler").jqxScheduler({
    date: new $.jqx.date(2019, 7, 17),
    width: 850,
    height: 600,
    source: adapter,
    view: 'monthView',
    showLegend: true,

    ready: function () {
        $("#scheduler").jqxScheduler('ensureAppointmentVisible', 1);
    },
    resources:
    {
        colorScheme: "scheme05",
        dataField: "calendar",
        source:  new $.jqx.dataAdapter(source)
    },
    appointmentDataFields:
    {
        from: "start",
        to: "end",
        id: 'id',
        description: "description",
        location: "location",
        subject: "subject",
        resourceId: "calendar"
    },
    views:
    [
        'dayView',
        'weekView',
        'monthView'
    ]
});
    $("#scheduler").on('appointmentDoubleClick', function (event) {
        var args = event.args;
        var appointment = args.appointment;
    });
    $('#scheduler').on('appointmentAdd', function (event) {
        var args = event.args;
        var appointment = args.appointment;
        var param = {}
        param['start'] = appointment.from.dateData;
        param['end'] = appointment.to.dateData;
        param['calendar'] = appointment.resourceId;
        param['description'] = appointment.description;
        param['location'] = appointment.location;
        param['subject'] = appointment.subject;

        $.post("/calendar/save",{data:JSON.stringify(param)},function(result){
            alert(result);
        });
    });


   $("#scheduler").on('appointmentChange', function (event) {
//   保存用
        var appointment = event.args.appointment;
        var data = getAddInfo(appointment);
        alert(data);
        $.post("/calendar/update",{data:data},function(result){
              alert(result);
        });

   });

   function getAddInfo(appointment){
        var param = {}
           param['start'] = appointment.from.dateData;
           param['end'] = appointment.to.dateData;
           param['calendar'] = appointment.resourceId;
           param['description'] = appointment.description;
           param['location'] = appointment.location;
           param['subject'] = appointment.subject;
           return JSON.stringify(param);
   }
});