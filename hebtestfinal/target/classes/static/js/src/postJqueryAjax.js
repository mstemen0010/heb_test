$(document).ready(function(){
	
	// SUBMIT FORM
    $("form").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		
		var fieldToSearch = $('input[name=searchOptions]:checked').val();
		// var selValue = $('input[name=rbnNumber]:checked').val(); 
		// alert("field to search is: " + fieldToSearch);
		var searchValue = $('input[name=searchText]').val();
		///alert("Search value is: " + searchValue);
		
		// do the query
		ajaxDoSearch(fieldToSearch, searchValue);
    
	});
    
    function ajaxDoSearch(searchField, searchString){
    	
    	var urlValue = "";
    	if( searchField == "*" && searchString == "*" ) {
    		urlValue = window.location + "api/items/all";
    	}else {
    		if( searchField.length <= 0 ) {
    			urlValue = window.location + "api/items/" +  searchField + "/all" + searchString;
    		} else {
    			urlValue = window.location + "api/items/" +  searchField + "/" + searchString;
    		}
    		
    	}
    	$('#itemTable tbody').empty();
    	// DO POST
    	$.ajax({
			type : "GET",
			url : urlValue,
			success : function(result) {
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
				$( "#matchedNumber" ).text( result.length );
				$("#infoAlert").removeClass('hidden');
			},
			error : function(e) {
				
				$("#infoAlert").text("No matches found for: " + searchString);
				$("#infoAlert").addClass('hidden');
			}
		});
    	
    }
    
});