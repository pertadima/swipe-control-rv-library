# swipe-control-rv-library
Library to make recyclerview swipeable


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
