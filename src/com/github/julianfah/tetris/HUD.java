package com.github.julianfah.tetris;

import com.github.julianfah.tetris.gameobject.Tile;
import com.github.julianfah.tetris.gameobject.Block;

import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class HUD extends JLabel 
{
  private static final long serialVersionUID = 5410681278520853843L;

  private static final int ROWS;
  private static final int COLS;
  private static final Point TILE_POS;

  private final int width;
  private final int height;
  private final int padding;
  private Tile tile;

  static{
    ROWS = 4;
    COLS = 4;
    TILE_POS = new Point(Game.PADDING, Game.PADDING);
  }

  /**
   * Constructs a new HUD with the next Tetris tile (and scoreboard)
   * @param width Width of the HUD
   * @param height Height of the HUD
   * @param padding Spacing to other elements in all 4 directions
   */
  public HUD(int width, int height, int padding) 
  {
    this.width = width;
    this.height = height;
    this.padding = padding;
    setPreferredSize(new Dimension(width + 2 * padding + 1, height + 2 * padding + 1));
  }

  public void render()
  {
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    // Background
    g.setColor(Color.WHITE);
    g.fillRect(padding, padding, width, height);

    // Grid lines
    g.setColor(Color.LIGHT_GRAY);
    for (int row = 0; row < ROWS + 1; row++)
      g.fillRect(0 + padding, row * Grid.BLOCKSIZE + padding, width, 1);
    for (int col = 0; col < COLS + 1; col++)
      g.fillRect(col * Grid.BLOCKSIZE + padding, 0 + padding, 1, height);

    // Tile
    for (Block block: tile.toBlockArray(0, TILE_POS))
      block.render(g);
  }

  public void setTile(Tile tile)
  {
    this.tile = tile;
  }
}
