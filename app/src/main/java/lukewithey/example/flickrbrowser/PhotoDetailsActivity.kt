package lukewithey.example.flickrbrowser

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_details.*


class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        activivateToolbar(true)

        val photo = intent.getParcelableExtra(PHOTO_TRANSFER) as Photo
        photo_title.text = photo.title
        photo_tags.text = photo.tags
        photo_author.text = photo.author

        Picasso.with(this).load(photo.link)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(photo_image)

    }

}
