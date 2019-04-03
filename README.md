# swipe-control-rv-library
Library to make recyclerview swipeable


| Current Version | Status |
| ------ | ------ |
| 0.1.0 | Stable |

# Add to your android project

Add to your build.gradle project level

```sh
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Add depedencies to your build.gradle module level

```sh
 implementation "com.github.pertadima:swipe-control-rv-library:$lib_version"
```
# How to use

Default use :
```sh
  val swipeHandler = object : SwipeControl(
                this@MainActivity,
                R.drawable.ic_edit, //for left icon
                R.drawable.ic_delete //for right icon
            ) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        SWIPE_RIGHT -> {
                            editItem(viewHolder)
                        }
                        SWIPE_LEFT -> {
                            deleteItem(viewHolder)
                        }
                    }
                }
            }
```

Change background color (add this code to the constructor):
```sh
    leftBackgroundColor = "#000000"
    rightBackgroundColor = "#000000"
```

Add to recyclerview :
```sh
    val itemTouchHelper = ItemTouchHelper(swipeHandler)
    itemTouchHelper.attachToRecyclerView(rv_name)
```

# Screenshoot :
![screenshoots](http://i66.tinypic.com/2sbs5qe.png)

# Implementation :
[Room-Kotlin](https://github.com/pertadima/room-kotlin-example)
