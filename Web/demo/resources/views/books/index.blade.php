<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    Hello
    <div>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            @foreach ($books as $book)
                <tr>
                    <td>{{ $book->name}}</td>
                    <td>{{ $book->author}}</td>
                    <td>{{ $book->quantity}}</td>
                    <td>{{ $book->price}}</td>
                    
                </tr>
            @endforeach
        </table>
    </div>
</body>
</html>