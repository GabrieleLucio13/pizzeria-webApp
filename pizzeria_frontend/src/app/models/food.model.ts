export class Food {
  type: string;
  name: string;
  ingredients: string;
  price: number;

  constructor(
    type: string,
    name: string,
    ingredients: string,
    price: number
  ) {
    this.type = type;
    this.name = name;
    this.ingredients = ingredients;
    this.price = price;
  }
}

  