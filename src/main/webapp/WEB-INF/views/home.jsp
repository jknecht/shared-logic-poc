<html>
	<script type="text/javascript" src="resources/js/jquery-1.7.1-min.js"></script>
	<script type="text/javascript" src="resources/js/underscore-1.3.1-min.js"></script>
	<script type="text/javascript" src="resources/js/json2.js"></script>
	<script type="text/javascript" src="resources/js/backbone-0.9.1-min.js"></script>
	<script type="text/javascript" src="resources/js/app/CarModel.js"></script>
	<script type="text/javascript">
	$(function() {
		
		var car = new Car({
			"id": "foo"
		});

		car.set("doors", 4);
		
		$("#saveCar").click(function(e) {
			e.preventDefault();
			alert(car.url());
			//var failures = car.validate();
			//if (failures && failures.length) {
			//	for (var i = 0; i < failures.length; i++) {
			//		alert(failures[i]);
			//	}
			//} else {
			//	alert('the car is ok');
			//}
			car.save({"doors": 4});
		});
	});
	</script>
	<a href="#" id="saveCar">Save Car</a>
	
	
</html>