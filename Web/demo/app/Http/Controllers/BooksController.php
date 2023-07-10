<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Books;

class BooksController extends Controller
{
    //make a index view
    public function index()
    {
        $books = Books::all();
        return view('books.index', ['books' => $books]);
    }
}
