document.getElementById('shortenBtn').addEventListener('click', function() {
    const originalUrl = document.getElementById('originalUrl').value;

    // Make an AJAX POST request to the server
    fetch('/api/shorten', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ originalUrl })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('shortenedUrl').value = data.shortenedUrl;
            document.getElementById('result').style.display = 'block';
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById('copyBtn').addEventListener('click', function() {
    const shortenedUrl = document.getElementById('shortenedUrl');
    shortenedUrl.select();
    document.execCommand('copy');
    alert('Copied to clipboard: ' + shortenedUrl.value);
});
