<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\BooksController;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

//mKE A ROUTE
Route::get('/index', [BooksController::class, 'index'])->name('books.index');
Route::get('/index/search', [BooksController::class, 'search'])->name('books.search');
Route::get('/index/create', [BooksController::class, 'create'])->name('books.create');
Route::post('/index/create', [BooksController::class, 'store'])->name('books.store');
Route::get('/index/{book}/edit', [BooksController::class, 'edit'])->name('books.edit');
Route::put('/index/{book}/update', [BooksController::class, 'update'])->name('books.update');
Route::delete('/index/{book}/delete', [BooksController::class, 'delete'])->name('books.delete');
