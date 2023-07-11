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

    //make a function for book searching
    public function search(Request $request)
    {
        $query = $request->search;
        $books = Books::where('name', 'LIKE', '%'. $query. '%')->get();
        
        return view('books.index', ['books' => $books]);
    }

    //make a function for book creation
    public function create()
    {
        return view('books.create');
    }
    //make a function for storing the created book
    public function store(Request $request)
    {
        //validate the data
        $data = $request->validate([
            'name' => 'required',
            'author' => 'required',
            'price' => 'required|decimal:0,2',
            'quantity' => 'required|numeric'
        ]);

        Books::create($data);

        return redirect(route('books.index'))->with('success', 'Succesfully created!');;
    }
    //method for delete
    public function delete(Books $book){
        $book->delete();
        return redirect(route('books.index'))->with('success', 'Succesfully deleted!');

    }
    public function edit(Books $book){
        return view('books.edit', ['book' => $book]);
    }
    public function update(Request $request, Books $book){
        $data = $request->validate([
            'name' => 'required',
            'author' => 'required',
            'price' => 'required|decimal:0,2',
            'quantity' => 'required|numeric'
        ]);

        $book->update($data);

        return redirect(route('books.index'))->with('success', 'Succesfully edited!');
    }
}
