package com.example.challengekosmosandroidcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.challengekosmosandroidcompose.R
import com.example.challengekosmosandroidcompose.domain.entities.Character

@Composable
fun MainScreen(
    uiState: ChallengeKosmosUiState,
    onDetailsClicked: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    val characters = uiState.characters
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small))
    ) {
        items(items = characters, key = {character -> character.id}) { character ->
            CharacterCard(
                character = character,
                onDetailsClicked = onDetailsClicked
            )
        }
    }
}

@Composable
fun CharacterCard(
    character: Character,
    onDetailsClicked: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))){
            Row {
                AsyncImage(
                    model = character.imageSource,
                    contentDescription = character.name,
                    modifier = Modifier.size(dimensionResource(R.dimen.image_size))
                    )
                Column {
                    Text(
                        text = stringResource(R.string.name),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = character.name,
                    )
                }
                Spacer(Modifier.weight(1F))
                Button(
                    onClick = { onDetailsClicked(character) }
                ) {
                    if(character.details)
                        Text(stringResource(R.string.hide_details))
                    else
                        Text(stringResource(R.string.show_details))
                }
            }
            if(character.details) CharacterDetails(character)
        }
    }

}

@Composable
fun CharacterDetails(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        CharacterDetail(detailName = stringResource(R.string.status), detailValue = character.status.toString())
        CharacterDetail(detailName = stringResource(R.string.species), detailValue = character.species.toString())
        CharacterDetail(detailName = stringResource(R.string.type), detailValue = character.type.toString())
        CharacterDetail(detailName = stringResource(R.string.gender), detailValue = character.gender.toString())
        CharacterDetail(detailName = stringResource(R.string.origin), detailValue = character.origin.toString())
        CharacterDetail(detailName = stringResource(R.string.location), detailValue = character.location.toString())
    }
}

@Composable
private fun CharacterDetail(
    detailName: String,
    detailValue: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = detailName,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = detailValue,
        )
    }
}
