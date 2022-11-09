$(function(){

const appendTask = function(data){
        var taskCode = '<a href="#" class="task-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#task-list')
            .append('<div>' + taskCode + '</div>');
    };

//Show adding task form
 $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');
    });

 //Adding task
 $('#save-task').click(function()
     {
         var data = $('#task-form form').serialize();
         $.ajax({
             method: "POST",
             url: '/tasks/',
             data: data,
             success: function(response)
             {
                 $('#task-form').css('display', 'none');
                 var task = {};
                 task.id = response;
                 var dataArray = $('#task-form form').serializeArray();
                 for(i in dataArray) {
                     task[dataArray[i]['name']] = dataArray[i]['value'];
                     task[dataArray[i]['date']] = dataArray[i]['value'];
                 }
                 appendTask(task);
             }
         });
         return false;
     });

 //Closing adding task form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

 });