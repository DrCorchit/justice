namespace justice.api.interfaces

//build namespace
//just an example -- you would never actually have to import the type Bool
Bool = justice.types.Bool
//finished building namespace

api interface Iterator<Type T> {
	imbued {
		field size -> Int
		field hasNext -> Bool
		routine next -> T
	}
}