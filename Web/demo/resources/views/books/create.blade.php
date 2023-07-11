<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    Create a book

    @if ($errors->any())
    <div>
        <p>
            <ul>
                @foreach ($errors->all() as $error)
                                <li>{{ $error }}</li>
                @endforeach
            </ul>
        </p>
    </div>
    @endif
    <div>
        <form action="{{ route('books.store') }}" method="POST">
            @csrf
            @method('post')

            <input type="text" name="name" placeholder="Name">
            <input type="text" name="author" placeholder="Author">
            <input type="text" name="price" placeholder="Price">
            <input type="text" name="quantity" placeholder="Quantity">
            <input type="submit" value="Create">
        </form>
    </div>
</body>
</html>