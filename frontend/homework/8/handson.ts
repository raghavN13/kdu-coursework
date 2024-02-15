const apiUrl = "https://dummyjson.com/recipes";
let fetchByNameUrl = "https://dummyjson.com/recipes/search?q="

interface Recipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    caloriesPerServing: number;
    timeToprepare?: number;

}
interface SimplifiedRecipe {
    name: string;
    image: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    timeToprepare?: number;
    caloriesPerServing: number;

}

interface ApiResponse {
    recipes: Recipe[];
}

const allRecipes: SimplifiedRecipe[] = [];


function addHTMLElements(recipes: SimplifiedRecipe[]) {
    console.log("going inside the add html elements tag");
    const container = document.getElementById('container');
    if (!container) {
        console.error("The container is null");
        return;
    }
    container.innerHTML = '';

    recipes.forEach(recipe => {
        const dataContainer = document.createElement('div');
        dataContainer.classList.add('single-container');

        // creating the image
        const image = document.createElement('img');
        image.src = recipe.image;
        image.alt = "A Picture of cuisine";
        dataContainer.appendChild(image);

        // creating the name
        const name = document.createElement('p');
        name.innerText = recipe.name;
        dataContainer.appendChild(name);

        // creating the rating
        const rating = document.createElement('p');
        rating.innerText = recipe.rating.toString();
        dataContainer.appendChild(rating);

        // creating the cuisine
        const cuisine = document.createElement('p');
        cuisine.innerText = recipe.cuisine;
        dataContainer.appendChild(cuisine);

        // creating the ingredients
        const ingredients = document.createElement('p');
        ingredients.innerText = recipe.ingredients.join(', ');
        dataContainer.appendChild(ingredients);
        ingredients.style.whiteSpace = 'pre-wrap'

        // creating the difficulty
        const difficulty = document.createElement('p');
        difficulty.innerText = recipe.difficulty;
        dataContainer.appendChild(difficulty);

        // creating the time to prepare
        if (recipe.timeToprepare != null) {
            const timeToPrepare = document.createElement('p');
            timeToPrepare.innerText = recipe.timeToprepare?.toString();
            dataContainer.appendChild(timeToPrepare);
        }

        // calories per serving
        const calories = document.createElement('p');
        calories.innerText = recipe.caloriesPerServing.toString();
        dataContainer.appendChild(calories);

        container.appendChild(dataContainer);
    });

    container.style.display = 'flex';
    container.style.justifyContent = 'space-between';
}


function mapRecipesToSimplified(recipes: Recipe[]) {
    const recipies: SimplifiedRecipe[] =
        recipes.map(recipe => ({
            name: recipe.name,
            image: recipe.image,
            rating: recipe.rating,
            cuisine: recipe.cuisine,
            ingredients: recipe.ingredients,
            difficulty: recipe.difficulty,
            timeToprepare: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
            caloriesPerServing: recipe.caloriesPerServing

        }))
    return recipies;
}

function printRecipes(recipes: SimplifiedRecipe[]) {
    recipes.forEach(recipe => {
        console.log(`
        Name: ${recipe.name}
        Image: ${recipe.image}
        Rating: ${recipe.rating}
        Cuisine: ${recipe.cuisine}
        Ingredients: ${recipe.ingredients.join(', ')}
        Difficulty: ${recipe.difficulty}
        Time to Prepare: ${recipe.timeToprepare} minutes
        Calories per Serving: ${recipe.caloriesPerServing}
    `);
    })

}



async function fetchRecipesFromAPI() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data: ApiResponse = await response.json();

        const simplifiedObject = mapRecipesToSimplified(data.recipes);


        allRecipes.push(...simplifiedObject);
        printRecipes(allRecipes);
        return simplifiedObject;
    } catch (error) {
        console.log("There was an error: ", error);
    }
}


async function fetchRecipeFromUsername(name: string) {
    fetchByNameUrl = `https://dummyjson.com/recipes/search?q=${name}`

    try {
        const response = await fetch(fetchByNameUrl);
        if (!response.ok) {
            console.log("Network response was not ok");
        }

        const data: ApiResponse = await response.json();
        const simplifiedObject = mapRecipesToSimplified(data.recipes);
        printRecipes(simplifiedObject);
        return simplifiedObject;



    }
    catch (error) {
        console.log("There was an error :", error);
    }

}
async function loadRecipesAndAddToPage() {
    console.log("entering here in load recipes");
    try {
        const recipes: SimplifiedRecipe[] | undefined = await fetchRecipesFromAPI();

        if (recipes !== undefined) {
            console.log(recipes);
            addHTMLElements(recipes);
        } else {
            console.log("An error occurred while fetching recipes.");
        }
    } catch (error) {
        console.log("An error occurred while loading recipes:", error);
    }
}


document.getElementById('submit-btn')?.addEventListener('click', async () => {
    const foodItem: HTMLInputElement | null = document.getElementById('search-bar') as HTMLInputElement | null;
    let foodItemName: string = '';
    if (foodItem != null) {
        foodItemName = foodItem.value;
    }
    const recipes: SimplifiedRecipe[] | undefined = await fetchRecipeFromUsername(foodItemName);

    if (recipes !== undefined) {
        console.log(recipes);
        addHTMLElements(recipes);
    } else {
        console.log("An error occurred while fetching recipes.");
    }
});
loadRecipesAndAddToPage();

