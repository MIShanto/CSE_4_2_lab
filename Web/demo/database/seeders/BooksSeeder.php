<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Str;

use App\Models\Books;

class BooksSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        for($i = 0; $i < 5; $i++){
            Books::create([
                'name' => Str::random(5),
                'author' => Str::random(5),
                'quantity' => random_int(0, 10),
                'price' => random_int(0, 10)
            ]);
        }
    }
}
