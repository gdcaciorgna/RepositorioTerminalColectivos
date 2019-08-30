(function calendario(){  

	$('.form_date').datetimepicker({
	    language:  'es',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
		format: 'dd/mm/yyyy'

	});
	$('.form_time').datetimepicker({
	    language:  'es',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
	});
 
}  )()
