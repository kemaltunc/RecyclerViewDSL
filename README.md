# RecyclerViewDSL

<h4>Example</h4>

<h6>Model</h6>
class Story(
    var title: String = "story"
) : RecyclerItem(R.layout.cell_story)

<h6>List</h6>
  val stories = listOf(
                Story(),
                Story(),
                Story()
            )


<h6>Sample Code</h6>

    val adapter = CustomAdapter.build<Story>(story_rv) {
            scrollDirection = DIRECTION.HORIZANTAL
        }.apply {

            bind { itemView, item, adapterPosition ->
                with(itemView) {
                    storyTitle.text = item.title
                }
            }
            onItemClickListener { item, adapterPosition ->

            }

            this.items = stories
        }
    
<h2>Add RecyclerviewDSL to your project</h2>
        <h6>Via Gradle</h6>
        
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

    implementation 'com.github.kemaltunc:RecyclerViewDSL:1.1.0'    
       

        
