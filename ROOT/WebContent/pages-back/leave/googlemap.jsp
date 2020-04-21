<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map-canvas { height: 100% }
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7J1zsErb9_7jxNu5KU5kIENFObAQEbl0&sensor=false">
    </script>
    <script type="text/javascript">
      var map;
      function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(13.727024, 100.528224),
          zoom: 14
        };
        
        map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
        
        var marker = new google.maps.Marker({
        	position:{lat:13.727024,lng:100.528224},
        	map:map
        });
        var infoWindow = new google.maps.InfoWindow({
        	content:'<h2>Current Position</h2>' 
        });
        marker.addListener('click',function(){
        	infoWindow.open(map,marker);
        });
      }
      
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div id="map-canvas"/>
     <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA2g8qURgn6qB59cUVrN9_2IriGPDp5B8Q&callback=initMap"
        async defer></script>
  </body>
</html>