var Car = Backbone.Model.extend({
	
	urlRoot : '/shared-logic-demo/cars',
	
	/*
	 * Confirms that the car is valid.
	 * Returns an array of failure codes
	 * or an empty array if all is well.
	 * 
	 * Possible failure codes:
	 *   DOOR_QTY_NOT_SET
	 *   DOOR_QTY_INVALID
	 */
	validate: function() {
		var failures = [];
		console.log("validating the car...");
		if (this.get("doors") === undefined) {
			failures.push("DOOR_QTY_NOT_SET");
		} else {
			var doors = this.get("doors");
			console.log("the car has " + doors + " doors.");
			if (doors !== 2 && doors !== 4) {
				failures.push("DOOR_QTY_INVALID");
			}
		}
		if (failures.length) {
			console.log("there are failures");
			return failures;			
		} else {
			console.log("the car is ok");
		}
	}

});