$(document).ready(function() {
	
	ajaxGet();
	
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : window.location + "api/items/all",
			success: function(result){
				$.each(result, function(i, item){
					var displayedPriceFixed = 0.0;
					var displayedCostFixed = 0.0;
					var displayedUnit = "";
					if( item.price > 0) {
						displayedPriceFixed = parseFloat(Math.round(item.price * 100) / 100).toFixed(2);
					}
					if( item.cost > 0) {
						displayedCostFixed = parseFloat(Math.round(item.cost * 100) / 100).toFixed(2);
					}
					if( item.unit == -1 || item.unit == 1) {
						item.unit == -1 ? displayedUnit = "lb" : displayedUnit = "Each"
					}
					var itemRow = '<tr>' +
										'<td>' + item.sku + '</td>' +
										'<td>' + item.description + '</td>' +
										'<td>' + item.lastSold + '</td>' +
										'<td>' + item.shelfLife + 'd' + '</td>' +
										'<td>' + item.department + '</td>' +
										'<td>' +'$'+ displayedPriceFixed + '</td>' +
										'<td>' + displayedUnit + '</td>' +
										'<td>' + item.xFor + '</td>' +									
										'<td>' +'$'+ displayedCostFixed + '</td>' +
									  '</tr>';
					
					$('#itemTable tbody').append(itemRow);
				}	
		        );
				
				$( "#itemTable tbody tr:odd" ).addClass("info");
				$( "#itemTable tbody tr:even" ).addClass("success");
				$( "#matchedNumber" ).text( result.length );
				$( "#infoAlert").removeClass('hidden');
			},
			error : function(e) {
				$("#infoAlert").text("No matches found for: *" );
				$("#infoAlert").addClass('hidden');
				console.log("ERROR: ", e);
			}
		});	
	}
})