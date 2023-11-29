document.getElementById('shortenBtn').addEventListener('click', function() {
    const originalUrl = document.getElementById('originalUrl').value;

    fetch('/api/shorten', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ originalUrl })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data && data.shortenedUrl) {
                document.getElementById('shortenedUrl').value = data.shortenedUrl;
                document.getElementById('result').style.display = 'block';
            } else {
                throw new Error('Invalid response from the server');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error processing your request.');
        });
});

document.getElementById('copyBtn').addEventListener('click', function() {
    const shortenedUrl = document.getElementById('shortenedUrl');
    shortenedUrl.select();
    document.execCommand('copy');
    alert('Copied to clipboard: ' + shortenedUrl.value);
});
