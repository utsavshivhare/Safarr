$(document).ready(function() {
    $('#add-location').click(function() {
        const location = {
            name: $('#location-name').val(),
            latitude: parseFloat($('#latitude').val()),
            longitude: parseFloat($('#longitude').val()),
            description: $('#description').val(),
            customText: $('#custom-text').val(),
            mapId: 1 // Set the current map ID
        };
        $.post('/api/locations', location, function(data) {
            console.log('Location added:', data);
        });
    });

    $('#upload-images').click(function() {
        const files = $('#image-input')[0].files;
        const locationId = 1; // Set the current location ID

        for (const file of files) {
            const formData = new FormData();
            formData.append('file', file);

            $.ajax({
                url: `/api/locations/${locationId}/images`,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function(data) {
                    console.log('Image uploaded:', data);
                }
            });
        }
    });
});
